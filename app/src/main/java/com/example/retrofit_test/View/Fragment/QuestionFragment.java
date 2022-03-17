package com.example.retrofit_test.View.Fragment;

import android.content.Intent;
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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_test.Common.QuestionsState;
import com.example.retrofit_test.Common.TagsChipHelper;
import com.example.retrofit_test.Model.Networking.ModelObject.DiffUtil.QuestionComparator;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Adapter.LoadStateAdapter;
import com.example.retrofit_test.View.Adapter.RecQuestionAdapter;
import com.example.retrofit_test.View.Custom.QuestionTagsDialog;
import com.example.retrofit_test.View.Custom.TagsDialog;
import com.example.retrofit_test.View.QuestionActivity;
import com.example.retrofit_test.ViewModel.QuestionFragmentViewModel;
import com.example.retrofit_test.databinding.FragmentQuestionBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.noties.markwon.Markwon;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class QuestionFragment extends Fragment{

    private static final String TAG = "HomeFragment";

    private RecQuestionAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private QuestionFragmentViewModel viewModel;
    private TextView noQuestionTextView;
    private ChipGroup chipGroup;
    private Disposable getQuestionDisposable;
    private TagsChipHelper tagsChipHelper;
    private Chip tagChip;


    // a boolean value to check if recyclerview needs to scroll to position 0 at refresh or its screen orientation.
    private boolean isRotation;

    private FragmentQuestionBinding binding;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView: ");

        boolean isBeingCreatedForFirstTime = savedInstanceState == null;

        binding = FragmentQuestionBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        init();

        if (!isBeingCreatedForFirstTime) {
            chipGroup.check(savedInstanceState.getInt("CheckedChip"));
            isRotation = true;
        }
        else {
            isRotation = false;
        }

        return view;
    }

    private void init() {
        tagsChipHelper = new TagsChipHelper(requireContext());
        viewModel = new ViewModelProvider(requireActivity()).get(QuestionFragmentViewModel.class);
        chipGroup = binding.chipGroupQuestionFragment;

        tagChip = binding.chipTagsQuestionFragment;

        refreshLayout = binding.swipeRefreshQuestionFragment;

        recyclerView = binding.recQuestionsQuestionFragment;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecQuestionAdapter(new QuestionComparator(), Markwon.create(requireContext()),question -> {
            Intent intent = new Intent(requireActivity(), QuestionActivity.class);
            intent.putExtra("questionId",question.getQuestionId());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter.withLoadStateFooter(new LoadStateAdapter(v -> adapter.retry())));
        adapter.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
        adapter.addLoadStateListener(loadStateListener);
        noQuestionTextView = binding.tvNoQuestionsQuestionFragment;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("CheckedChip",chipGroup.getCheckedChipId());
    }

    PublishSubject<CombinedLoadStates> subject = PublishSubject.create();
    Disposable loadStateDisposable =
            subject.distinctUntilChanged(CombinedLoadStates::getRefresh)
                    .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                    .subscribe(combinedLoadStates -> {
                        if (combinedLoadStates.getRefresh() instanceof LoadState.NotLoading) {
                            refreshLayout.setRefreshing(false);
                            if (adapter.getItemCount() > 0) {
                                noQuestionTextView.setVisibility(View.INVISIBLE);
                                if (!isRotation)
                                    recyclerView.scrollToPosition(0);
                                else
                                    isRotation = false;
                            }
                            else {
                                noQuestionTextView.setText(R.string.no_questions_found);
                                noQuestionTextView.setVisibility(View.VISIBLE);
                            }
                        }
                        if (combinedLoadStates.getRefresh() instanceof LoadState.Loading) {
                            refreshLayout.setRefreshing(true);
                            noQuestionTextView.setVisibility(View.INVISIBLE);
                        }
                        else if (combinedLoadStates.getRefresh() instanceof LoadState.Error) {
                            refreshLayout.setRefreshing(false);
                            noQuestionTextView.setVisibility(View.VISIBLE);
                            noQuestionTextView.setText(((LoadState.Error) combinedLoadStates.getRefresh()).getError().getMessage());
                        }
                    });

    private final Function1<CombinedLoadStates,Unit> loadStateListener = combinedLoadStates -> {
        subject.onNext(combinedLoadStates);
        return null;
    };

    @Override
    public void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: " );
        chipGroup.setOnCheckedChangeListener(checkedChangeListener);
        tagChip.setOnClickListener(tagChipClickListener);
        refreshLayout.setOnRefreshListener(onRefreshListener);

        getQuestionDisposable = viewModel.getQuestionFlowable(requireActivity())
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(questionPagingData -> adapter.submitData(getLifecycle(),questionPagingData));

        viewModel.setTagsDialogListener(tagsDialogListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: " );
        chipGroup.setOnCheckedChangeListener(null);
        tagChip.setOnClickListener(null);
        refreshLayout.setOnRefreshListener(null);

        getQuestionDisposable.dispose();

    }


    @Override
    public void onDestroy() {
        loadStateDisposable.dispose();
        super.onDestroy();
    }


    private final SwipeRefreshLayout.OnRefreshListener onRefreshListener = () -> adapter.refresh();

    private final View.OnClickListener tagChipClickListener = (view) -> {
        if (isAdded()) {
            DialogFragment tagsDialog = new QuestionTagsDialog();
            tagsDialog.show(getParentFragmentManager(),TagsDialog.TAG);
        }
    };

    private final TagsDialog.TagsDialogListener tagsDialogListener = tags -> {
        String tagsString = tagsChipHelper.convertTagsListToString(tags.getStringArrayList("tagsList"));
        viewModel.setTags(tagsString);
        adapter.refresh();
    };

    private final ChipGroup.OnCheckedChangeListener checkedChangeListener = (group, checkedId) -> {
        Log.e(TAG, "chip check listener: " );
        if (checkedId == R.id.chip_newest_question_fragment)
            viewModel.setQuestionsState(QuestionsState.NEWEST);
        if (checkedId == R.id.chip_bountied_question_fragment)
            viewModel.setQuestionsState(QuestionsState.BOUNTIED);
        if (checkedId == R.id.chip_unanswered_question_fragment)
            viewModel.setQuestionsState(QuestionsState.UNANSWERED);
        Log.e(TAG, "chip check listener: " + adapter );
        adapter.refresh();
    };

}

