package com.example.retrofit_test.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.example.retrofit_test.Model.ApiRepository;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import java.util.List;

public class HomeFragmentViewModel extends AndroidViewModel {

   // private final Application application;
    private MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    private final ApiRepository repository;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);
   //     this.application = application;
        repository = new ApiRepository();
    }

    public MutableLiveData<List<Question>> getQuestions() {
        if (questions.getValue() == null || questions.getValue().size() == 0)
         questions.setValue(fetchData());
        return questions;
    }

    public List<Question> fetchData() {
        questions = repository.getQuestion();
        return questions.getValue();
    }

}
