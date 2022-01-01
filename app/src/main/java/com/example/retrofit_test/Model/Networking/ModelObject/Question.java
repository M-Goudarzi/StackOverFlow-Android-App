package com.example.retrofit_test.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;

public class Question {

    @SerializedName("tags")
    private List<String> tags = null;
    @SerializedName("bounty_amount")
    private Integer bountyAmount;
    @SerializedName("view_count")
    private Integer viewCount;
    @SerializedName("up_vote_count")
    private Integer upVoteCount;
    @SerializedName("answer_count")
    private Integer answerCount;
    @SerializedName("question_id")
    private String questionId;
    @SerializedName("title")
    private String title;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(Integer upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBountyAmount() {
        return bountyAmount;
    }

    public void setBountyAmount(Integer bountyAmount) {
        this.bountyAmount = bountyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(tags, question.tags) && Objects.equals(bountyAmount, question.bountyAmount) && Objects.equals(viewCount, question.viewCount) && Objects.equals(upVoteCount, question.upVoteCount) && Objects.equals(answerCount, question.answerCount) && Objects.equals(questionId, question.questionId) && Objects.equals(title, question.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tags, bountyAmount, viewCount, upVoteCount, answerCount, questionId, title);
    }
}
