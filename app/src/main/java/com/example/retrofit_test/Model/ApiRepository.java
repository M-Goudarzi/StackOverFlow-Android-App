package com.example.retrofit_test.Model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.retrofit_test.Common.FetchQuestionsCallback;
import com.example.retrofit_test.Common.QuestionsState;
import com.example.retrofit_test.Model.Networking.BaseRetrofit;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.ModelObject.StackExchangeQuestion;
import com.example.retrofit_test.Model.Networking.StackExchangeApi;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {

    private BaseRetrofit baseRetrofit;
    private final StackExchangeApi api;
    private final MutableLiveData<List<Question>> questions;
    private Call<StackExchangeQuestion> call;


    public ApiRepository() {
        if (baseRetrofit == null)
            baseRetrofit = new BaseRetrofit();

        questions = new MutableLiveData<>();
        api = baseRetrofit.getApi();
    }

    public MutableLiveData<List<Question>> getQuestion(String tags,int state ,FetchQuestionsCallback fetchQuestionsCallback) {
        switch (state) {
            case QuestionsState.BOUNTIED:
                call = api.getBountiedQuestions(tags);
                break;
            case QuestionsState.UNANSWERED:
                    call = api.getUnAnsweredQuestions(tags);
                    break;
            case QuestionsState.NEWEST:
            default:
                call = api.getNewestQuestions(tags);
        }


        call.enqueue(new Callback<StackExchangeQuestion>() {
            @Override
            public void onResponse(@NonNull Call<StackExchangeQuestion> call, @NonNull Response<StackExchangeQuestion> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() == null)
                    return;
                questions.setValue(response.body().getQuestions());
                fetchQuestionsCallback.onFetchComplete();
            }
            @Override
            public void onFailure(@NonNull Call<StackExchangeQuestion> call, @NonNull Throwable t) {

            }
        });
        return questions;
    }

    public void closeCall() {
        if (call == null)
            return;
        if (call.isCanceled())
            return;
        call.cancel();
    }

}
