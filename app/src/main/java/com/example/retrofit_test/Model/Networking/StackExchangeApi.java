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

    @GET("questions?order=desc&sort=activity&site=stackoverflow&filter=!Fc6b9.zWynF*47QU6m.lYry.Cr")
    Call<StackExchangeQuestion> getNewestQuestions(@Query("tagged") String tags);

    @GET("questions/featured?order=desc&sort=activity&site=stackoverflow&filter=!)Q0(G*2aFvT.dFn_krg9e_JA")
    Call<StackExchangeQuestion> getBountiedQuestions(@Query("tagged") String tags);

    @GET("questions/unanswered?order=desc&sort=activity&site=stackoverflow&filter=!)Q0(G*2aFvT.dFn_krg9e_JA")
    Call<StackExchangeQuestion> getUnAnsweredQuestions(@Query("tagged") String tags);

}
