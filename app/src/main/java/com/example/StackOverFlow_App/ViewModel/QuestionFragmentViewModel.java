package com.example.StackOverFlow_App.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;
import com.example.StackOverFlow_App.Other.QuestionsState;
import com.example.StackOverFlow_App.Model.Networking.BaseRetrofit;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Question;
import com.example.StackOverFlow_App.Model.Networking.StackExchangeApi;
import com.example.StackOverFlow_App.Model.Paging.QuestionPagingSource;
import com.example.StackOverFlow_App.View.Custom.TagsDialog;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class QuestionFragmentViewModel extends AndroidViewModel {

    private TagsDialog.TagsDialogListener tagsDialogListener;
    private final QuestionsState questionsState;
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
                new PagingConfig(20, 20, false, 60),
                () -> new QuestionPagingSource(api, viewModelStoreOwner));
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(QuestionFragmentViewModel.this);
        flowable = PagingRx.getFlowable(pager);
        return PagingRx.cachedIn(flowable, viewModelScope);
    }

    public int getQuestionsState() {
        return questionsState.getState();
    }

    public void setQuestionsState(int state) {
        questionsState.setState(state);
    }

    public void setTags(String tags) {
        questionsState.setTags(tags);
    }

    public String getTags() {
        return questionsState.getTags();
    }

    public TagsDialog.TagsDialogListener getTagsDialogListener() {
        return tagsDialogListener;
    }

    public void setTagsDialogListener(TagsDialog.TagsDialogListener tagsDialogListener) {
        this.tagsDialogListener = tagsDialogListener;
    }

}
