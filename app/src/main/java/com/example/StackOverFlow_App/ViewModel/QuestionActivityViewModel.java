package com.example.StackOverFlow_App.ViewModel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.StackOverFlow_App.Model.Networking.BaseRetrofit;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.QuestionResponse;
import com.example.StackOverFlow_App.Model.Networking.StackExchangeApi;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class QuestionActivityViewModel extends ViewModel {

    private final StackExchangeApi api;
    private final MutableLiveData<QuestionResponse> question = new MutableLiveData<>();
    private String questionId;
    private QuestionByIdErrorCallBack callBack;

    public QuestionActivityViewModel() {
        BaseRetrofit baseRetrofit = new BaseRetrofit();
        api = baseRetrofit.getApi();
    }

    public MutableLiveData<QuestionResponse> getQuestion(String questionId, Lifecycle lifecycle, QuestionByIdErrorCallBack callBack) {
        this.questionId = questionId;
        this.callBack = callBack;
        if (question.getValue() == null)
            fetchQuestion(questionId,lifecycle,callBack);
        return question;
    }

    public MutableLiveData<QuestionResponse> retryQuestionFetch(Lifecycle lifecycle) {
        if (questionId != null || callBack != null)
            fetchQuestion(questionId,lifecycle,callBack);
        return question;
    }

    private void fetchQuestion(String questionId, Lifecycle lifecycle,QuestionByIdErrorCallBack callBack) {
        api.getQuestionById(questionId)
                .subscribeOn(Schedulers.io())
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                .subscribe(new SingleObserver<QuestionResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull QuestionResponse questionResponse) {
                        question.postValue(questionResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onError(e);
                    }
                });
    }

public interface QuestionByIdErrorCallBack {
        void onError(Throwable e);
}

}

