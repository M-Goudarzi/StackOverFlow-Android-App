package com.example.retrofit_test;

import com.example.retrofit_test.ModelObject.StackExchangeQuestion;

import retrofit2.Call;
import retrofit2.http.GET;

interface StackExchangeApi {

    @GET("/2.3/questions?order=desc&sort=activity&site=stackoverflow")
    Call<StackExchangeQuestion> getQuestions();

}
