package com.example.StackOverFlow_App.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class QuestionResponse {

    @SerializedName("items")
    private final List<Question> questions = null;
    @SerializedName("has_more")
    private Boolean hasMore;
    @SerializedName("quota_max")
    private Integer quotaMax;
    @SerializedName("quota_remaining")
    private Integer quotaRemaining;
    @SerializedName("page")
    private Integer page;

    public List<Question> getQuestions() {
        return questions;
    }
    public Boolean getHasMore() {
        return hasMore;
    }
    public Integer getQuotaMax() {
        return quotaMax;
    }
    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }
    public Integer getPage() {
        return page;
    }
}
