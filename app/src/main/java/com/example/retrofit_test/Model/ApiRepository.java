package com.example.retrofit_test.Model;

import com.example.retrofit_test.Model.Networking.BaseRetrofit;
import com.example.retrofit_test.Model.Networking.ModelObject.StackExchangeQuestion;
import com.example.retrofit_test.Model.Networking.StackExchangeApi;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;

public class ApiRepository {

    private BaseRetrofit baseRetrofit;
    private final StackExchangeApi api;

    private Map<String , String> questionQueryMap;


    public ApiRepository() {
        if (baseRetrofit == null)
            baseRetrofit = new BaseRetrofit();

        api = baseRetrofit.getApi();

        initQueryMap();
    }

    public void getQuestion(Callback<StackExchangeQuestion> callback) {
        Call<StackExchangeQuestion> call = api.getQuestions(questionQueryMap);
        call.enqueue(callback);
    }


    private void initQueryMap() {
        questionQueryMap = new HashMap<>();
        questionQueryMap.put("order" , "desc");
        questionQueryMap.put("sort" , "activity");
        questionQueryMap.put("site" , "stackoverflow");

    }

}
