package com.example.retrofit_test.ViewModel;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit_test.Model.Networking.BaseRetrofit;
import com.example.retrofit_test.Model.Networking.ModelObject.QuestionResponse;
import com.example.retrofit_test.Model.Networking.ModelObject.UserResponse;
import com.example.retrofit_test.Model.Networking.ModelObject.UserWithQuestions;
import com.example.retrofit_test.Model.Networking.StackExchangeApi;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserProfileActivityViewModel extends ViewModel {

    private String userId;
    private UserByIdErrorCallBack callBack;
    private final StackExchangeApi api;
    private final MutableLiveData<UserWithQuestions> userWithQuestionsMutableLiveData = new MutableLiveData<>();

    public UserProfileActivityViewModel() {
        BaseRetrofit baseRetrofit = new BaseRetrofit();
        api = baseRetrofit.getApi();
    }

    public MutableLiveData<UserWithQuestions> getUser(String userId,Lifecycle lifecycle,UserByIdErrorCallBack callBack) {
        this.userId = userId;
        this.callBack = callBack;
        if (userWithQuestionsMutableLiveData.getValue() == null)
            fetchUser(userId,lifecycle,callBack);
        return userWithQuestionsMutableLiveData;
    }

    public MutableLiveData<UserWithQuestions> retry(Lifecycle lifecycle) {
        if (userId != null || callBack != null)
            fetchUser(userId,lifecycle,callBack);
        return userWithQuestionsMutableLiveData;
    }

    private void fetchUser(String userId,Lifecycle lifecycle,UserByIdErrorCallBack callBack) {

        Observable<UserResponse> userResponseSingle = api.getUserById(userId);
        Observable<QuestionResponse> questionResponseSingle = api.getQuestionsOfUserById(userId);

        Observable<UserWithQuestions> userWithQuestionsObservable = Observable.zip(
                userResponseSingle.subscribeOn(Schedulers.io()),
                questionResponseSingle.subscribeOn(Schedulers.io()),
                (userResponse, questionResponse) -> {

                    UserWithQuestions userWithQuestions = new UserWithQuestions();
                    userWithQuestions.setUserResponse(userResponse);
                    userWithQuestions.setQuestionResponse(questionResponse);

                    return userWithQuestions;
                }
        );

        userWithQuestionsObservable.subscribeOn(Schedulers.io())
                .to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
                .subscribe(new Observer<UserWithQuestions>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull UserWithQuestions userWithQuestions) {
                        userWithQuestionsMutableLiveData.postValue(userWithQuestions);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    public interface UserByIdErrorCallBack {
        void onError(Throwable e);
    }
}
