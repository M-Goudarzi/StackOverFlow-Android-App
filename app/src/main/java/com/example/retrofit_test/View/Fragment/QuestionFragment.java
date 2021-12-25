package com.example.retrofit_test.View.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.retrofit_test.Common.FetchQuestionsCallback;
import com.example.retrofit_test.Common.QuestionsState;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Adapter.RecQuestionQuestionAdapter;
import com.example.retrofit_test.View.Custom.TagsDialog;
import com.example.retrofit_test.ViewModel.QuestionFragmentViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RecQuestionQuestionAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private QuestionFragmentViewModel viewModel;
    private ProgressBar progressBar;
    private View view;
    private TextView noQuestionTextVie;
    private ChipGroup chipGroup;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        boolean isBeingCreatedForFirstTime = savedInstanceState == null;

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_question, container, false);

        init();

        if (!isBeingCreatedForFirstTime) {
            chipGroup.check(savedInstanceState.getInt("CheckedChip"));
            progressBar.setVisibility(View.INVISIBLE);
        }

        viewModel.getQuestions(fetchQuestionsCallback).observe(getViewLifecycleOwner(), questions -> {
            addQuestionToList((ArrayList<Question>) questions);
        });

        viewModel.setTagsDialogListener(tagsDialogListener);

        return view;
    }

    private void init() {
        viewModel = new ViewModelProvider(requireActivity()).get(QuestionFragmentViewModel.class);
        chipGroup = view.findViewById(R.id.chip_group_home);
        chipGroup.setOnCheckedChangeListener(checkedChangeListener);
  //      setUpDialog();
        Chip tagChip = view.findViewById(R.id.chip_tags);
        tagChip.setOnClickListener(tagChipClickListener);
        progressBar = view.findViewById(R.id.progress_home);
        refreshLayout = view.findViewById(R.id.swipe_refresh_main);
        refreshLayout.setOnRefreshListener(onRefreshListener);
        RecyclerView recyclerView = view.findViewById(R.id.rec_home_questions);
        ArrayList<Question> questions = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecQuestionQuestionAdapter(questions,requireContext());
        recyclerView.setAdapter(adapter);
        noQuestionTextVie = view.findViewById(R.id.tv_no_questions_home);
    }

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> {
        addQuestionToList(fetchQuestions());
        refreshLayout.setRefreshing(false);
    };

    private final View.OnClickListener tagChipClickListener = (view) -> {
        if (isAdded()) {
            DialogFragment tagsDialog = new TagsDialog();
            tagsDialog.show(getParentFragmentManager(),"tagsDialog");
        }
    };

    // Convert an array of tags to one String of tags separated by ';'
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

    private final Handler handler = new Handler();

    private void addQuestionToList(ArrayList<Question> questions) {
        if (questions == null) {
            int delay = 1000;
            handler.postDelayed(() -> addQuestionToList(fetchQuestions()), delay);
        }
        else {
            if (questions.isEmpty())
                noQuestionTextVie.setVisibility(View.VISIBLE);
            else
                noQuestionTextVie.setVisibility(View.INVISIBLE);

            adapter.setQuestions(questions);
        }
    }

    private ArrayList<Question> fetchQuestions() {
        progressBar.setVisibility(View.VISIBLE);
        ArrayList<Question> questions = null;
        if (viewModel != null){
            questions = (ArrayList<Question>) viewModel.fetchData(fetchQuestionsCallback);
        }
        return questions;
    }

    private final FetchQuestionsCallback fetchQuestionsCallback = () -> progressBar.setVisibility(View.INVISIBLE);

    private final ChipGroup.OnCheckedChangeListener checkedChangeListener = (group, checkedId) -> {
        if (checkedId == R.id.chip_newest)
            viewModel.setQuestionsState(QuestionsState.NEWEST);
        if (checkedId == R.id.chip_bountied)
            viewModel.setQuestionsState(QuestionsState.BOUNTIED);
        if (checkedId == R.id.chip_unanswered)
            viewModel.setQuestionsState(QuestionsState.UNANSWERED);

        addQuestionToList(fetchQuestions());
    };

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CheckedChip",chipGroup.getCheckedChipId());
    }


    private final TagsDialog.TagsDialogListener tagsDialogListener = tags -> {
        String tagsString = buildTagString(tags);
        viewModel.setQuestionsTags(tagsString);

        addQuestionToList(fetchQuestions());

    };

}

