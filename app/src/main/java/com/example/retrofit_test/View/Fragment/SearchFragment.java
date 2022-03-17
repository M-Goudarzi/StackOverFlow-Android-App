package com.example.retrofit_test.View.Fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.retrofit_test.Common.TagsChipHelper;
import com.example.retrofit_test.Model.DB.Entity.Search;
import com.example.retrofit_test.View.Adapter.RecSearchHistoryAdapter;
import com.example.retrofit_test.View.Custom.SearchTagsDialog;
import com.example.retrofit_test.View.Custom.TagsDialog;
import com.example.retrofit_test.View.SearchResultActivity;
import com.example.retrofit_test.ViewModel.SearchFragmentViewModel;
import com.example.retrofit_test.databinding.FragmentSearchBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.switchmaterial.SwitchMaterial;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchFragment extends Fragment {
    private static final String TAG = "SearchFragment";

    private EditText searchEdittext,numberOfAnswersEdittext,titleEdittext,bodyEdittext;
    private ConstraintLayout filtersLayout;
    private SwitchMaterial acceptedSwitch,closedSwitch;
    boolean isFilterLayoutVisible;
    private SearchFragmentViewModel viewModel;
    private RecSearchHistoryAdapter adapter;
    private TagsChipHelper tagsChipHelper;
    private ChipGroup tagsChipGroup;
    private ArrayList<Chip> selectedChips;

    private FragmentSearchBinding binding;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        init();

        if (savedInstanceState == null) {
            isFilterLayoutVisible = false;
        }
        else {
            isFilterLayoutVisible = savedInstanceState.getBoolean("isFilterLayoutVisible");
            addSelectedTagsToChipGroup(savedInstanceState.getString("selectedChipsStaring"));
        }

        if (isFilterLayoutVisible)
            filtersLayout.setVisibility(View.VISIBLE);
        else
            filtersLayout.setVisibility(View.GONE);

        viewModel.getSearchHistory().observe(getViewLifecycleOwner(), searches -> {
            adapter.setSearchList((ArrayList<Search>) searches);
        });

        viewModel.setTagsDialogListener(tagsDialogListener);

        return view;
    }

    private void init() {
        tagsChipHelper = new TagsChipHelper(requireContext());
        selectedChips = new ArrayList<>();
        viewModel = new ViewModelProvider(requireActivity()).get(SearchFragmentViewModel.class);
        ImageView deleteSearchButton = binding.ivDeleteHistorySearchFragment;
        searchEdittext = binding.searchEdittextSearchFragment;
        tagsChipGroup = binding.tagsChipGroupSearchFragment;
        Chip addTagsChip = binding.addTagsChipSearchFragment;
        addTagsChip.setOnClickListener(tagChipClickListener);
        Button filtersButton = binding.filterButtonSearchFragment;
        Button searchButton = binding.searchButtonSearchFragment;
        searchButton.setOnClickListener(searchButtonClickListener);
        filtersButton.setOnClickListener(filterButtonClickListener);
        filtersLayout = binding.filterLayoutSearchFragment;
        acceptedSwitch = binding.hasAnswerSwitchSearchFragment;
        closedSwitch = binding.closedSwitchSearchFragment;
        numberOfAnswersEdittext = binding.numberOfAnswersEdittext;
        titleEdittext = binding.titleContainsSearchFragment;
        bodyEdittext = binding.bodyContainsSearchFragment;
        RecyclerView recyclerView = binding.recQuestionsSearchFragment;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<Search> searchList = new ArrayList<>();
        adapter = new RecSearchHistoryAdapter(searchList,getActivity());
        recyclerView.setAdapter(adapter);
        deleteSearchButton.setOnClickListener(v -> {
            viewModel.deleteSearchHistory();
            adapter.notifyDataSetChanged();
        });
    }

    private final View.OnClickListener tagChipClickListener = (view) -> {
        if (isAdded()) {
            DialogFragment tagsDialog = new SearchTagsDialog();
            tagsDialog.show(getParentFragmentManager(),"tagsDialog");
        }
    };

    private final TagsDialog.TagsDialogListener tagsDialogListener = tags -> {
        String tagsString = tagsChipHelper.convertTagsListToString(tags.getStringArrayList("tagsList"));
        viewModel.setTags(tagsString);

        addSelectedTagsToChipGroup(tagsString);
    };

    private void addSelectedTagsToChipGroup(String tagsString) {
        clearSelectedChipsList();
        if (tagsString.isEmpty())
            return;
        List<String> tagsList = tagsChipHelper.convertTagsStringToList(tagsString);
        Chip chip;
        for (String tags : tagsList) {
            chip = tagsChipHelper.createChip(tags);
            addChipToSelectedListAndSetClickListener(chip);
            tagsChipGroup.addView(chip);
        }
    }

    private void clearSelectedChipsList() {
        selectedChips.clear();
        for (int i = 0; i < tagsChipGroup.getChildCount(); i++) {
            if (tagsChipGroup.getChildAt(i) != binding.addTagsChipSearchFragment) {
                tagsChipGroup.removeViewAt(i);
                i -= 1;
            }
        }
    }

    private void addChipToSelectedListAndSetClickListener(Chip chip) {
                selectedChips.add(chip);
                chip.setOnClickListener(selectedChipsClickListener);
    }

    private final View.OnClickListener selectedChipsClickListener = view -> {
        for (Chip chip : selectedChips){
            if (chip.getId() == view.getId()){
                selectedChips.remove(chip);
                tagsChipGroup.removeView(chip);
                viewModel.removeATag(chip.getText().toString());
                return;
            }
        }
    };

    private boolean searchIsAcceptedBool;
    private boolean searchIsClosedBool;
    private int searchNumberOfAnswers;
    private String searchTitleContains;
    private String searchBodyContains;
    private String searchQuery;

    private final View.OnClickListener searchButtonClickListener = view -> {
        getSearchPropertiesFromUi();
        saveSearchHistory();
        startSearchResultActivity();
    };

    private void getSearchPropertiesFromUi() {
        searchQuery = searchEdittext.getText().toString().toLowerCase(Locale.ROOT);
        searchIsAcceptedBool = acceptedSwitch.isChecked();
        searchIsClosedBool = closedSwitch.isChecked();
        searchNumberOfAnswers = Integer.parseInt((!numberOfAnswersEdittext.getText().toString().equals("")
                ? numberOfAnswersEdittext.getText().toString() : "0"));
        searchTitleContains = titleEdittext.getText().toString().toLowerCase(Locale.ROOT);
        searchBodyContains = bodyEdittext.getText().toString().toLowerCase(Locale.ROOT);
    }
    private void saveSearchHistory() {
        viewModel.setHasAccepted(searchIsAcceptedBool);
        viewModel.setClosed(searchIsClosedBool);
        viewModel.setMinimumAnswers(searchNumberOfAnswers);
        viewModel.setTitleContains(searchTitleContains);
        viewModel.setBodyContains(searchBodyContains);
        viewModel.insertSearch(searchQuery);
    }
    private void startSearchResultActivity() {
        Intent intent = new Intent(getActivity(), SearchResultActivity.class);
        intent.putExtra("searchQuery",searchQuery);
        intent.putExtra("searchTags",viewModel.getTags());
        intent.putExtra("searchIsAcceptedBool",searchIsAcceptedBool);
        intent.putExtra("searchIsClosedBool",searchIsClosedBool);
        intent.putExtra("searchNumberOfAnswers",searchNumberOfAnswers);
        intent.putExtra("searchTitleContains",searchTitleContains);
        intent.putExtra("searchBodyContains",searchBodyContains);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
    }

    private final View.OnClickListener filterButtonClickListener = view -> {
        if (isFilterLayoutVisible) {
            filtersLayout.setVisibility(View.GONE);
            isFilterLayoutVisible = false;
        }
        else {
            filtersLayout.setVisibility(View.VISIBLE);
            isFilterLayoutVisible = true;
        }
    };


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFilterLayoutVisible",isFilterLayoutVisible);
        outState.putString("selectedChipsStaring",viewModel.getTags());
    }
}