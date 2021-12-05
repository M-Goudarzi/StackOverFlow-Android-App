package com.example.retrofit_test.Common;

public class QuestionsState {

    public static final int NEWEST = 0;
    public static final int BOUNTIED = 1;
    public static final int UNANSWERED = 2;

    private static int state = 0;
    private static String tags = "";

    public void setState(int state) {
        switch (state) {
            case BOUNTIED:
                QuestionsState.state = BOUNTIED;
                break;
            case UNANSWERED:
                QuestionsState.state = UNANSWERED;
                break;
            case NEWEST:
            default:
                QuestionsState.state = NEWEST;
        }
    }

    public void setTags(String tags) {
        QuestionsState.tags = tags;
    }

    public int getState() {
        return state;
    }

    public String getTags() {
        return tags;
    }




}
