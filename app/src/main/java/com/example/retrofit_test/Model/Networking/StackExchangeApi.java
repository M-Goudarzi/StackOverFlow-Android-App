package com.example.retrofit_test.Model.Networking;

import com.example.retrofit_test.Model.Networking.ModelObject.QuestionResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface StackExchangeApi {

    @GET("questions?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!4)Lbteely(YAdQsz8")
    Single<QuestionResponse> getNewestQuestionsWithPaging(@Query("tagged") String tags, @Query("page") Integer page);

    @GET("questions/featured?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!4)Lbteely(YAdQsz8")
    Single<QuestionResponse> getBountiedQuestionsWithPaging(@Query("tagged") String tags, @Query("page") Integer page);

    @GET("questions/unanswered?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!4)Lbteely(YAdQsz8")
    Single<QuestionResponse> getUnAnsweredQuestionsWithPaging(@Query("tagged") String tags, @Query("page") Integer page);

}
