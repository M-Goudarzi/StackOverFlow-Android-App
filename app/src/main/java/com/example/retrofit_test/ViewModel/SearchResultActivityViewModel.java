package com.example.retrofit_test.ViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.retrofit_test.Common.QuestionSearchFilter;
import com.example.retrofit_test.Model.Networking.BaseRetrofit;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.StackExchangeApi;
import com.example.retrofit_test.Model.Paging.QuestionPagingSource;
import com.example.retrofit_test.Model.Paging.SearchQuestionPagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class SearchResultActivityViewModel extends ViewModel {

    private Flowable<PagingData<Question>> flowable;
    private final StackExchangeApi api;

    public SearchResultActivityViewModel() {
        BaseRetrofit baseRetrofit = new BaseRetrofit();
        this.api = baseRetrofit.getApi();
    }

    public Flowable<PagingData<Question>> getQuestionFlowable(String searchQuery, QuestionSearchFilter searchFilter) {
        if (flowable == null)
            flowable = fetchQuestionFlowable(searchQuery,searchFilter);
        return flowable;
    }

    private Flowable<PagingData<Question>> fetchQuestionFlowable(String searchQuery, QuestionSearchFilter searchFilter) {
        Pager<Integer, Question> pager = new Pager<>(
                new PagingConfig(20, 20, false, 60),
                () -> new SearchQuestionPagingSource(api,searchQuery,searchFilter));
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(SearchResultActivityViewModel.this);
        flowable = PagingRx.getFlowable(pager);
        return PagingRx.cachedIn(flowable, viewModelScope);
    }

}
