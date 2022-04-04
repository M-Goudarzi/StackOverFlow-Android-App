package com.example.StackOverFlow_App.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.CombinedLoadStates;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.StackOverFlow_App.Other.QuestionSearchFilter;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.DiffUtil.QuestionComparator;
import com.example.StackOverFlow_App.R;
import com.example.StackOverFlow_App.View.Adapter.LoadStateAdapter;
import com.example.StackOverFlow_App.View.Adapter.RecQuestionPagingAdapter;
import com.example.StackOverFlow_App.ViewModel.SearchResultActivityViewModel;
import com.example.StackOverFlow_App.databinding.ActivitySearchResultBinding;
import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.noties.markwon.Markwon;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class SearchResultActivity extends AppCompatActivity {

    private ActivitySearchResultBinding binding;

    private Intent intent;
    private RecQuestionPagingAdapter adapter;
    private SearchResultActivityViewModel viewModel;
    private String searchQuery;
    private QuestionSearchFilter searchFilter;
    private TextView noQuestionTextView;
    private SwipeRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchResultBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        intent = getIntent();

        init();

        viewModel.getQuestionFlowable(searchQuery,searchFilter)
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                .subscribe(questionPagingData -> adapter.submitData(getLifecycle(),questionPagingData));
    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(SearchResultActivityViewModel.class);
        noQuestionTextView = binding.tvNoQuestionsSearchResult;
        refreshLayout = binding.swipeRefreshSearchResult;
        searchQuery = intent.getStringExtra("searchQuery");
        if (searchQuery == null)
            searchQuery = "";
        searchFilter = new QuestionSearchFilter();
        String searchTags = intent.getStringExtra("searchTags");
        boolean searchOnlyOneTag = intent.getBooleanExtra("searchOnlyOneTag",false);
        if (searchOnlyOneTag)
            binding.tvToolbarSearchResult.setText(searchTags);
        boolean searchIsAcceptedBool = intent.getBooleanExtra("searchIsAcceptedBool", false);
        boolean searchIsClosedBool = intent.getBooleanExtra("searchIsClosedBool", false);
        int searchNumberOfAnswers = intent.getIntExtra("searchNumberOfAnswers", 0);
        String searchTitleContains = intent.getStringExtra("searchTitleContains");
        String searchBodyContains = intent.getStringExtra("searchBodyContains");
        searchFilter.setTags(searchTags != null ? searchTags : "");
        searchFilter.setBodyContains(searchBodyContains != null ? searchBodyContains : "");
        searchFilter.setTitleContains(searchTitleContains != null ? searchTitleContains : "");
        searchFilter.setClosed(searchIsClosedBool);
        searchFilter.setHasAccepted(searchIsAcceptedBool);
        searchFilter.setMinimumAnswers(searchNumberOfAnswers);
        RecyclerView recyclerView = binding.recQuestionsSearchResult;
        adapter = new RecQuestionPagingAdapter(new QuestionComparator(), Markwon.create(this), question -> {
            Intent intent = new Intent(this, QuestionActivity.class);
            intent.putExtra("questionId",question.getQuestionId());
            startActivity(intent);
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter.withLoadStateFooter(new LoadStateAdapter(view -> adapter.retry())));
        adapter.addLoadStateListener(loadStateListener);
        binding.arrowBackSearchResult.setOnClickListener(v -> {
            finish();
            overridePendingTransition(0,0);
        });
    }

    PublishSubject<CombinedLoadStates> subject = PublishSubject.create();
    Disposable loadStateDisposable =
            subject.distinctUntilChanged(CombinedLoadStates::getRefresh)
                    .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this)))
                    .subscribe(combinedLoadStates -> {
                        if (combinedLoadStates.getRefresh() instanceof LoadState.NotLoading) {
                            refreshLayout.setRefreshing(false);
                            refreshLayout.setEnabled(false);
                            if (adapter.getItemCount() > 0) {
                                noQuestionTextView.setVisibility(View.INVISIBLE);
                            }
                            else {
                                noQuestionTextView.setText(R.string.no_questions_found);
                                noQuestionTextView.setVisibility(View.VISIBLE);
                            }
                        }
                        if (combinedLoadStates.getRefresh() instanceof LoadState.Loading) {
                            refreshLayout.setEnabled(true);
                            refreshLayout.setRefreshing(true);
                            noQuestionTextView.setVisibility(View.INVISIBLE);
                        }
                        else if (combinedLoadStates.getRefresh() instanceof LoadState.Error) {
                            refreshLayout.setRefreshing(false);
                            refreshLayout.setEnabled(false);
                            noQuestionTextView.setVisibility(View.VISIBLE);
                            noQuestionTextView.setText(((LoadState.Error) combinedLoadStates.getRefresh()).getError().getMessage());
                        }
                    });

    private final Function1<CombinedLoadStates, Unit> loadStateListener = combinedLoadStates -> {
        subject.onNext(combinedLoadStates);
        return null;
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadStateDisposable.dispose();
    }
}