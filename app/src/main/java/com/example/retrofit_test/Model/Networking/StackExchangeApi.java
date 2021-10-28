package com.example.retrofit_test.Model.Networking;

import com.example.retrofit_test.Model.Networking.ModelObject.StackExchangeQuestion;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface StackExchangeApi {

    @GET("questions")
    Call<StackExchangeQuestion> getQuestions(@QueryMap Map<String , String> queryMap);

}
