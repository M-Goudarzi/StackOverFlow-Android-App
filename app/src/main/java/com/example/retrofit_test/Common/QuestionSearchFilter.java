package com.example.retrofit_test.Common;

public class QuestionSearchFilter {

    private boolean hasAccepted;
    private boolean closed;
    private int minimumAnswers;
    private String titleContains;
    private String bodyContains;
    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean getHasAccepted() {
        return hasAccepted;
    }

    public void setHasAccepted(boolean hasAccepted) {
        this.hasAccepted = hasAccepted;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getMinimumAnswers() {
        return minimumAnswers;
    }

    public void setMinimumAnswers(int minimumAnswers) {
        this.minimumAnswers = minimumAnswers;
    }

    public String getTitleContains() {
        return titleContains;
    }

    public void setTitleContains(String titleContains) {
        this.titleContains = titleContains;
    }

    public String getBodyContains() {
        return bodyContains;
    }

    public void setBodyContains(String bodyContains) {
        this.bodyContains = bodyContains;
    }
}
