package com.example.retrofit_test.View.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.CombinedLoadStates;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.retrofit_test.Common.QuestionsState;
import com.example.retrofit_test.Model.Networking.ModelObject.DiffUtil.QuestionComparator;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Adapter.LoadStateAdapter;
import com.example.retrofit_test.View.Adapter.RecQuestionQuestionAdapter;
import com.example.retrofit_test.View.Custom.TagsDialog;
import com.example.retrofit_test.ViewModel.QuestionFragmentViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.noties.markwon.Markwon;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class QuestionFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private RecQuestionQuestionAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private QuestionFragmentViewModel viewModel;
    private View view;
    private TextView noQuestionTextVie;
    private ChipGroup chipGroup;
    private Disposable getQuestionDisposable;

    public QuestionFragment() {
        // Required empty public constructor
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
        }

       getQuestionDisposable = viewModel.getQuestionFlowable(requireActivity())
               .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
               .subscribe(questionPagingData -> adapter.submitData(getLifecycle(),questionPagingData));

        viewModel.setTagsDialogListener(tagsDialogListener);

        return view;
    }

    private void init() {
        viewModel = new ViewModelProvider(requireActivity()).get(QuestionFragmentViewModel.class);
        chipGroup = view.findViewById(R.id.chip_group_home);
        chipGroup.setOnCheckedChangeListener(checkedChangeListener);
        Chip tagChip = view.findViewById(R.id.chip_tags);
        tagChip.setOnClickListener(tagChipClickListener);
        refreshLayout = view.findViewById(R.id.swipe_refresh_main);
        refreshLayout.setOnRefreshListener(onRefreshListener);
        recyclerView = view.findViewById(R.id.rec_home_questions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecQuestionQuestionAdapter(new QuestionComparator(), Markwon.create(requireContext()));
        recyclerView.setAdapter(adapter.withLoadStateFooter(new LoadStateAdapter(v -> adapter.retry())));
        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        adapter.addLoadStateListener(loadStateListener);
        noQuestionTextVie = view.findViewById(R.id.tv_no_questions_home);
    }

    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> adapter.refresh();

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

    private final ChipGroup.OnCheckedChangeListener checkedChangeListener = (group, checkedId) -> {
        if (checkedId == R.id.chip_newest)
            viewModel.setQuestionsState(QuestionsState.NEWEST);
        if (checkedId == R.id.chip_bountied)
            viewModel.setQuestionsState(QuestionsState.BOUNTIED);
        if (checkedId == R.id.chip_unanswered)
            viewModel.setQuestionsState(QuestionsState.UNANSWERED);

        adapter.refresh();
    };

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CheckedChip",chipGroup.getCheckedChipId());
    }


    private final TagsDialog.TagsDialogListener tagsDialogListener = tags -> {
        String tagsString = buildTagString(tags);
        viewModel.setQuestionsTags(tagsString);

        adapter.refresh();
    };
    PublishSubject<CombinedLoadStates> subject = PublishSubject.create();
    Disposable loadStateDisposable =
            subject.distinctUntilChanged(CombinedLoadStates::getRefresh)
                    .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                    .subscribe(combinedLoadStates -> {
                        if (combinedLoadStates.getRefresh() instanceof LoadState.NotLoading) {
                            refreshLayout.setRefreshing(false);
                            if (adapter.getItemCount() > 0) {
                                noQuestionTextVie.setVisibility(View.INVISIBLE);
                                recyclerView.scrollToPosition(0);
                            }
                            else {
                                noQuestionTextVie.setText(R.string.no_questions_found);
                                noQuestionTextVie.setVisibility(View.VISIBLE);
                            }
                        }
                        if (combinedLoadStates.getRefresh() instanceof LoadState.Loading) {
                            refreshLayout.setRefreshing(true);
                            noQuestionTextVie.setVisibility(View.INVISIBLE);
                        }
                        else if (combinedLoadStates.getRefresh() instanceof LoadState.Error) {
                            refreshLayout.setRefreshing(false);
                            noQuestionTextVie.setVisibility(View.VISIBLE);
                            noQuestionTextVie.setText(((LoadState.Error) combinedLoadStates.getRefresh()).getError().getMessage());
                        }
                    });

    private final Function1<CombinedLoadStates,Unit> loadStateListener = combinedLoadStates -> {
        subject.onNext(combinedLoadStates);
        return null;
    };

    @Override
    public void onDestroy() {
        getQuestionDisposable.dispose();
        loadStateDisposable.dispose();
        super.onDestroy();
    }
}

