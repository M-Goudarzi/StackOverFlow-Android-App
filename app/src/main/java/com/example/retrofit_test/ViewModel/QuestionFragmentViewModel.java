package com.example.retrofit_test.ViewModel;

import android.app.Application;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofit_test.Common.FetchQuestionsCallback;
import com.example.retrofit_test.Common.QuestionsState;
import com.example.retrofit_test.Model.ApiRepository;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.View.Custom.TagsDialog;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragmentViewModel extends AndroidViewModel {

   // private final Application application;
    private MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    private final ApiRepository repository;
    private final QuestionsState questionsState;
    private TagsDialog.TagsDialogListener tagsDialogListener;

    public QuestionFragmentViewModel(@NonNull Application application) {
        super(application);
        //    this.application = application;
        repository = new ApiRepository();
        questionsState = new QuestionsState();

    }

    public MutableLiveData<List<Question>> getQuestions(FetchQuestionsCallback fetchQuestionsCallback) {
        if (questions.getValue() == null || questions.getValue().size() == 0)
         questions.setValue(fetchData(fetchQuestionsCallback));
        return questions;
    }

    public List<Question> fetchData(FetchQuestionsCallback fetchQuestionsCallback) {
        questions = repository.getQuestion(getQuestionsTags(),getQuestionsState(),fetchQuestionsCallback);
        return questions.getValue();
    }

    public void closeNetworkCall() {
        if (repository == null)
            return;
        repository.closeCall();
    }

    public String getQuestionsTags() {
        return questionsState.getTags();
    }

    private int getQuestionsState() {
        return questionsState.getState();
    }

    public void setQuestionsState(int state) {
        questionsState.setState(state);
    }
    public void setQuestionsTags(String tags) {
        questionsState.setTags(tags);
    }

    public TagsDialog.TagsDialogListener getTagsDialogListener() {
        return tagsDialogListener;
    }

    public void setTagsDialogListener(TagsDialog.TagsDialogListener tagsDialogListener) {
        this.tagsDialogListener = tagsDialogListener;
    }
}
