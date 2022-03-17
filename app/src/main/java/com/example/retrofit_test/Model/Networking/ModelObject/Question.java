package com.example.retrofit_test.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;

public class Question {

    @SerializedName("owner")
    private Owner owner;
    @SerializedName("answers")
    private List<Answer> answers = null;
    @SerializedName("is_answered")
    private Boolean isAnswered;
    @SerializedName("down_vote_count")
    private Integer downVoteCount;
    @SerializedName("accepted_answer_id")
    private Integer acceptedAnswerId;
    @SerializedName("last_activity_date")
    private Integer lastActivityDate;
    @SerializedName("creation_date")
    private Integer creationDate;
    @SerializedName("body_markdown")
    private String bodyMarkdown;
    @SerializedName("link")
    private String link;
    @SerializedName("body")
    private String body;
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
    @SerializedName("comments")
    private List<Comment> comments = null;


    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    public Integer getDownVoteCount() {
        return downVoteCount;
    }

    public void setDownVoteCount(Integer downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public Integer getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(Integer acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    public Integer getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Integer lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Integer getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Integer creationDate) {
        this.creationDate = creationDate;
    }

    public String getBodyMarkdown() {
        return bodyMarkdown;
    }

    public void setBodyMarkdown(String bodyMarkdown) {
        this.bodyMarkdown = bodyMarkdown;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getBountyAmount() {
        return bountyAmount;
    }

    public void setBountyAmount(Integer bountyAmount) {
        this.bountyAmount = bountyAmount;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(owner, question.owner) && Objects.equals(answers, question.answers) && Objects.equals(isAnswered, question.isAnswered) && Objects.equals(downVoteCount, question.downVoteCount) && Objects.equals(acceptedAnswerId, question.acceptedAnswerId) && Objects.equals(lastActivityDate, question.lastActivityDate) && Objects.equals(creationDate, question.creationDate) && Objects.equals(bodyMarkdown, question.bodyMarkdown) && Objects.equals(link, question.link) && Objects.equals(body, question.body) && Objects.equals(tags, question.tags) && Objects.equals(bountyAmount, question.bountyAmount) && Objects.equals(viewCount, question.viewCount) && Objects.equals(upVoteCount, question.upVoteCount) && Objects.equals(answerCount, question.answerCount) && Objects.equals(questionId, question.questionId) && Objects.equals(title, question.title) && Objects.equals(comments, question.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, answers, isAnswered, downVoteCount, acceptedAnswerId, lastActivityDate, creationDate, bodyMarkdown, link, body, tags, bountyAmount, viewCount, upVoteCount, answerCount, questionId, title, comments);
    }
}
