package com.example.retrofit_test.View.Fragment;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.example.retrofit_test.Common.FetchQuestionsCallback;
import com.example.retrofit_test.Common.QuestionsState;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Adapter.RecHomeQuestionAdapter;
import com.example.retrofit_test.View.Custom.TagsDialog;
import com.example.retrofit_test.ViewModel.HomeFragmentViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private QuestionsState tagState;
    private RecHomeQuestionAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private HomeFragmentViewModel viewModel;
    private ProgressBar progressBar;
    private View view;
    private DialogFragment tagsDialog;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        init();

        viewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);

        if (savedInstanceState != null)
        progressBar.setVisibility(View.INVISIBLE);

        viewModel.getQuestions(tagState.getTags(),tagState.getState(),fetchQuestionsCallback).observe(getViewLifecycleOwner(), questions -> {
            addQuestionToList((ArrayList<Question>) questions);
        });

        return view;
    }

    private void init() {
        ChipGroup chipGroup = view.findViewById(R.id.chip_group_home);
        chipGroup.setOnCheckedChangeListener(checkedChangeListener);
        tagState = new QuestionsState();
        setUpDialog();
        Chip tagChip = view.findViewById(R.id.chip_tags);
        tagChip.setOnClickListener(tagChipClickListener);
        progressBar = view.findViewById(R.id.progress_home);
        refreshLayout = view.findViewById(R.id.swipe_refresh_main);
        refreshLayout.setOnRefreshListener(onRefreshListener);
        RecyclerView recyclerView = view.findViewById(R.id.rec_home_questions);
        ArrayList<Question> questions = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecHomeQuestionAdapter(questions);
        recyclerView.setAdapter(adapter);
    }

    private void setUpDialog() {
        tagsDialog = new TagsDialog(listener,tagState.getTags());
    }

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> {
        if (fetchQuestions() != null)
        addQuestionToList(fetchQuestions());
        refreshLayout.setRefreshing(false);
    };

    private final View.OnClickListener tagChipClickListener = (view) -> {
        if (isAdded())
        tagsDialog.show(getFragmentManager(),"tagsDialog");
    };

    private final TagsDialog.TagsDialogListener listener = tags -> {
        String tagsString = buildTagString(tags);
        tagState.setTags(tagsString);
        if (fetchQuestions() != null)
        addQuestionToList(fetchQuestions());
    };

    private String buildTagString(ArrayList<String> tags) {
        StringBuilder tagsString = new StringBuilder();
        for (String tag : tags) {
            if (tags.get(0).equals(tag))
                tagsString = new StringBuilder(tag);
            else {
                tagsString.append(";").append(tag);
            }
        }
        return tagsString.toString();
    }

    private void addQuestionToList(ArrayList<Question> questions) {
        adapter.setQuestions(questions);
    }

    private ArrayList<Question> fetchQuestions() {
        progressBar.setVisibility(View.VISIBLE);
        ArrayList<Question> questions = null;
        if (viewModel != null && viewModel.fetchData(tagState.getTags(),tagState.getState(),fetchQuestionsCallback) != null){
            questions = (ArrayList<Question>) viewModel.fetchData(tagState.getTags(),tagState.getState(),fetchQuestionsCallback);
        }
        return questions;
    }

    private final FetchQuestionsCallback fetchQuestionsCallback = () -> progressBar.setVisibility(View.INVISIBLE);

    private final ChipGroup.OnCheckedChangeListener checkedChangeListener = (group, checkedId) -> {
        if (checkedId == R.id.chip_newest)
            tagState.setState(QuestionsState.NEWEST);
        if (checkedId == R.id.chip_bountied)
            tagState.setState(QuestionsState.BOUNTIED);
        if (checkedId == R.id.chip_unanswered)
            tagState.setState(QuestionsState.UNANSWERED);
        if (fetchQuestions() != null)
        addQuestionToList(fetchQuestions());

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel == null)
            return;
        viewModel.closeNetworkCall();
    }
}

