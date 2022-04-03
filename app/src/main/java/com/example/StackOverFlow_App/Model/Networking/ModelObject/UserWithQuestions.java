package com.example.StackOverFlow_App.Model.Networking.ModelObject;

public class UserWithQuestions {

    private UserResponse userResponse;
    private QuestionResponse questionResponse;

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public QuestionResponse getQuestionResponse() {
        return questionResponse;
    }

    public void setQuestionResponse(QuestionResponse questionResponse) {
        this.questionResponse = questionResponse;
    }
}
