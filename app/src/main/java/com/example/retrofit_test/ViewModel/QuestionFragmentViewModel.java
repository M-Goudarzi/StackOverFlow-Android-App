package com.example.retrofit_test.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;
import com.example.retrofit_test.Common.QuestionsState;
import com.example.retrofit_test.Model.Networking.BaseRetrofit;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.StackExchangeApi;
import com.example.retrofit_test.Model.Paging.QuestionPagingSource;
import com.example.retrofit_test.View.Custom.TagsDialog;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class QuestionFragmentViewModel extends AndroidViewModel {

    private final QuestionsState questionsState;
    private TagsDialog.TagsDialogListener tagsDialogListener;
    private Flowable<PagingData<Question>> flowable;
    private final StackExchangeApi api;

    public QuestionFragmentViewModel(@NonNull Application application) {
        super(application);
        BaseRetrofit baseRetrofit = new BaseRetrofit();
        api = baseRetrofit.getApi();
        questionsState = new QuestionsState();
    }

    public Flowable<PagingData<Question>> getQuestionFlowable(ViewModelStoreOwner viewModelStoreOwner) {
        if (flowable == null)
            flowable = fetchQuestionFlowable(viewModelStoreOwner);
        return flowable;
    }

    private Flowable<PagingData<Question>> fetchQuestionFlowable(ViewModelStoreOwner viewModelStoreOwner) {
        Pager<Integer, Question> pager = new Pager<>(
                new PagingConfig(20, 20, false, 60,80),
                () -> new QuestionPagingSource(api, viewModelStoreOwner));
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(QuestionFragmentViewModel.this);
        flowable = PagingRx.getFlowable(pager);
        return PagingRx.cachedIn(flowable, viewModelScope);
    }

    public String getQuestionsTags() {
        return questionsState.getTags();
    }

    public int getQuestionsState() {
        return questionsState.getState();
    }

    public void setQuestionsState(int state) {
        questionsState.setState(state);
    }

    public void setQuestionsTags(String tags) {
        questionsState.setTags(tags);
    }

    public TagsDialog.TagsDialogListener getTagsDialogListener() {
        return tagsDialogListener;
    }

    public void setTagsDialogListener(TagsDialog.TagsDialogListener tagsDialogListener) {
        this.tagsDialogListener = tagsDialogListener;
    }
}
