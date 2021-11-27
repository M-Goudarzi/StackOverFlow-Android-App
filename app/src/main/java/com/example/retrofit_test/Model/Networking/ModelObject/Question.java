package com.example.retrofit_test.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;

public class Question {


    @SerializedName("tags")
    private List<String> tags = null;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("is_answered")
    private Boolean isAnswered;
    @SerializedName("view_count")
    private Integer viewCount;
    @SerializedName("answer_count")
    private Integer answerCount;
    @SerializedName("score")
    private Integer score;
    @SerializedName("last_activity_date")
    private Integer lastActivityDate;
    @SerializedName("creation_date")
    private Integer creationDate;
    @SerializedName("last_edit_date")
    private Integer lastEditDate;
    @SerializedName("question_id")
    private String questionId;
    @SerializedName("content_license")
    private String contentLicense;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private String title;
    @SerializedName("closed_date")
    private Integer closedDate;
    @SerializedName("closed_reason")
    private String closedReason;
    @SerializedName("accepted_answer_id")
    private Integer acceptedAnswerId;

    public List<String> getTags() {
        return tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public Boolean getIsAnswered() {
        return isAnswered;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public Integer getScore() {
        return score;
    }

    public Integer getLastActivityDate() {
        return lastActivityDate;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public Integer getLastEditDate() {
        return lastEditDate;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getContentLicense() {
        return contentLicense;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public Integer getClosedDate() {
        return closedDate;
    }

    public String getClosedReason() {
        return closedReason;
    }

    public Integer getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(tags, question.tags) && Objects.equals(owner, question.owner) && Objects.equals(isAnswered, question.isAnswered) && Objects.equals(viewCount, question.viewCount) && Objects.equals(answerCount, question.answerCount) && Objects.equals(score, question.score) && Objects.equals(lastActivityDate, question.lastActivityDate) && Objects.equals(creationDate, question.creationDate) && Objects.equals(lastEditDate, question.lastEditDate) && Objects.equals(questionId, question.questionId) && Objects.equals(contentLicense, question.contentLicense) && Objects.equals(link, question.link) && Objects.equals(title, question.title) && Objects.equals(closedDate, question.closedDate) && Objects.equals(closedReason, question.closedReason) && Objects.equals(acceptedAnswerId, question.acceptedAnswerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tags, owner, isAnswered, viewCount, answerCount, score, lastActivityDate, creationDate, lastEditDate, questionId, contentLicense, link, title, closedDate, closedReason, acceptedAnswerId);
    }
}
