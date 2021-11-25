package com.example.retrofit_test.Model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.retrofit_test.Model.Networking.BaseRetrofit;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.ModelObject.StackExchangeQuestion;
import com.example.retrofit_test.Model.Networking.StackExchangeApi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiRepository {

    private BaseRetrofit baseRetrofit;
    private final StackExchangeApi api;
    private final MutableLiveData<List<Question>> questions;
    private Map<String , String> questionQueryMap;


    public ApiRepository() {
        if (baseRetrofit == null)
            baseRetrofit = new BaseRetrofit();

        questions = new MutableLiveData<>();
        api = baseRetrofit.getApi();

        initQueryMap();
    }

    public MutableLiveData<List<Question>> getQuestion() {
        Call<StackExchangeQuestion> call = api.getQuestions(questionQueryMap);
        call.enqueue(new Callback<StackExchangeQuestion>() {
            @Override
            public void onResponse(@NonNull Call<StackExchangeQuestion> call, @NonNull Response<StackExchangeQuestion> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() == null)
                    return;
                questions.setValue(response.body().getQuestions());
            }
            @Override
            public void onFailure(@NonNull Call<StackExchangeQuestion> call, @NonNull Throwable t) {

            }
        });
        return questions;
    }


    private void initQueryMap() {
        questionQueryMap = new HashMap<>();
        questionQueryMap.put("order" , "desc");
        questionQueryMap.put("sort" , "activity");
        questionQueryMap.put("site" , "stackoverflow");

    }

}
