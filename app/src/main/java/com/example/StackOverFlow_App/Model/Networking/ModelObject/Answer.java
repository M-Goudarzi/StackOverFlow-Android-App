package com.example.StackOverFlow_App.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Answer {

    @SerializedName("owner")
    private Owner owner;
    @SerializedName("comment_count")
    public Integer commentCount;
    @SerializedName("down_vote_count")
    private Integer downVoteCount;
    @SerializedName("up_vote_count")
    private Integer upVoteCount;
    @SerializedName("is_accepted")
    private Boolean isAccepted;
    @SerializedName("creation_date")
    private Integer creationDate;
    @SerializedName("answer_id")
    private Integer answerId;
    @SerializedName("body_markdown")
    private String bodyMarkdown;
    @SerializedName("comments")
    private List<Comment> comments = null;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Integer getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(Integer downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public Integer getUpVoteCount() {
        return upVoteCount;
    }

    public void setUpVoteCount(Integer upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getBodyMarkdown() {
        return bodyMarkdown;
    }

    public void setBodyMarkdown(String bodyMarkdown) {
        this.bodyMarkdown = bodyMarkdown;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
