package com.example.retrofit_test.Model.Networking;

import com.example.retrofit_test.Model.Networking.ModelObject.StackExchangeQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface StackExchangeApi {

    @GET("questions?order=desc&sort=activity&site=stackoverflow&filter=!4)Lbteely(YAdQsz8")
    Call<StackExchangeQuestion> getNewestQuestions(@Query("tagged") String tags);

    @GET("questions/featured?order=desc&sort=activity&site=stackoverflow&filter=!4)Lbteely(YAdQsz8")
    Call<StackExchangeQuestion> getBountiedQuestions(@Query("tagged") String tags);

    @GET("questions/unanswered?order=desc&sort=activity&site=stackoverflow&filter=!4)Lbteely(YAdQsz8")
    Call<StackExchangeQuestion> getUnAnsweredQuestions(@Query("tagged") String tags);

}
