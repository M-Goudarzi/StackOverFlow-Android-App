package com.example.retrofit_test.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.retrofit_test.Common.FetchQuestionsCallback;
import com.example.retrofit_test.Model.ApiRepository;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {

   // private final Application application;
    private MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    private final ApiRepository repository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
        //    this.application = application;
        repository = new ApiRepository();
    }

    public MutableLiveData<List<Question>> getQuestions(String tags,int state,FetchQuestionsCallback fetchQuestionsCallback) {
        if (questions.getValue() == null || questions.getValue().size() == 0)
         questions.setValue(fetchData(tags,state,fetchQuestionsCallback));
        return questions;
    }

    public List<Question> fetchData(String tags,int state ,FetchQuestionsCallback fetchQuestionsCallback) {
        questions = repository.getQuestion(tags,state,fetchQuestionsCallback);
        return questions.getValue();
    }

    public void closeNetworkCall() {
        if (repository == null)
            return;
        repository.closeCall();
    }

}
