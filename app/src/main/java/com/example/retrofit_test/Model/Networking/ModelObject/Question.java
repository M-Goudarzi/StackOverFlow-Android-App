package com.example.retrofit_test.Model.Networking.ModelObject;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Objects;

public class Question {


    @SerializedName("tags")
    private List<String> tags = null;
    @SerializedName("owner")
    private Owner owner;
    @SerializedName("question_id")
    private String questionId;
    @SerializedName("title")
    private String title;

    public List<String> getTags() {
        return tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(tags, question.tags) && Objects.equals(owner, question.owner) && Objects.equals(questionId, question.questionId) && Objects.equals(title, question.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tags, owner, questionId, title);
    }
}
