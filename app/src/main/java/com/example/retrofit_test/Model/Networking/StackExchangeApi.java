package com.example.retrofit_test.Model.Networking;

import com.example.retrofit_test.Model.Networking.ModelObject.QuestionResponse;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StackExchangeApi {

    @GET("questions?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!)rp_N76JL2.c(stRx_hf")
    Single<QuestionResponse> getNewestQuestionsWithPaging(
                @Query("tagged") String tags,
                @Query("page") Integer page);

    @GET("questions/featured?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!)rp_N76JL2.c(stRx_hf")
    Single<QuestionResponse> getBountiedQuestionsWithPaging(
            @Query("tagged") String tags,
            @Query("page") Integer page);

    @GET("questions/unanswered?order=desc&sort=activity&site=stackoverflow&pagesize=20&filter=!)rp_N76JL2.c(stRx_hf")
    Single<QuestionResponse> getUnAnsweredQuestionsWithPaging(
            @Query("tagged") String tags,
            @Query("page") Integer page);

    @GET("/2.3/search/advanced?pagesize=20&order=desc&sort=activity&site=stackoverflow&filter=!)rp_N76JL2.c(stRx_hf")
    Single<QuestionResponse> search(
             @Query("page") Integer page,
             @Query("q") String searchQuery,
             @Query("accepted") boolean hasAccepted,
             @Query("closed") boolean isClosed,
             @Query("answers") int minimumAnswers,
             @Query("body") String bodyContains,
             @Query("title") String titleContains,
             @Query("tagged") String tags);

    @GET("questions/{id}?order=desc&sort=activity&site=stackoverflow&filter=!)OS2treGqbOpL7cr-eYBTAD2yY5iompxkGw(P6g1NqtXqOKXr0Lvg81GKU-YuPAHfUoygj")
    Single<QuestionResponse> getQuestionById(@Path("id") String id);

}
