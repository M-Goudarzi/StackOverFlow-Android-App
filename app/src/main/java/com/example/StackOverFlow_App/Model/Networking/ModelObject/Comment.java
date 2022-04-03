package com.example.StackOverFlow_App.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("owner")
    public Owner owner;
    @SerializedName("reply_to_user")
    private Owner replyToUser;
    @SerializedName("creation_date")
    public Integer creationDate;
    @SerializedName("comment_id")
    public Integer commentId;
    @SerializedName("body_markdown")
    public String bodyMarkdown;
    @SerializedName("body")
    public String body;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getReplyToUser() {
        return replyToUser;
    }

    public void setReplyToUser(Owner replyToUser) {
        this.replyToUser = replyToUser;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getBodyMarkdown() {
        return bodyMarkdown;
    }

    public void setBodyMarkdown(String bodyMarkdown) {
        this.bodyMarkdown = bodyMarkdown;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
