package com.example.StackOverFlow_App.Model.Networking;

import com.example.StackOverFlow_App.Model.Networking.ModelObject.QuestionResponse;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.UserResponse;
import com.example.StackOverFlow_App.Other.Constant;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StackExchangeApi {

    @GET(Constant.getNewestQuestionsWithPagingEndPointUrl)
    Single<QuestionResponse> getNewestQuestionsWithPaging(
                @Query("tagged") String tags,
                @Query("page") Integer page);

    @GET(Constant.getBountiedQuestionsWithPagingEndPointUrl)
    Single<QuestionResponse> getBountiedQuestionsWithPaging(
            @Query("tagged") String tags,
            @Query("page") Integer page);

    @GET(Constant.getUnAnsweredQuestionsWithPagingEndPointUrl)
    Single<QuestionResponse> getUnAnsweredQuestionsWithPaging(
            @Query("tagged") String tags,
            @Query("page") Integer page);

    @GET(Constant.searchEndPointUrl)
    Single<QuestionResponse> search(
             @Query("page") Integer page,
             @Query("q") String searchQuery,
             @Query("accepted") boolean hasAccepted,
             @Query("closed") boolean isClosed,
             @Query("answers") int minimumAnswers,
             @Query("body") String bodyContains,
             @Query("title") String titleContains,
             @Query("tagged") String tags);

    @GET(Constant.getQuestionByIdEndPointUrl)
    Single<QuestionResponse> getQuestionById(@Path("id") String id);

    @GET(Constant.getUserByIdEndPointUrl)
    Observable<UserResponse> getUserById(@Path("id") String id);

    @GET(Constant.getQuestionsOfUserByIdEndPointUrl)
    Observable<QuestionResponse> getQuestionsOfUserById(@Path("id") String id);

}
