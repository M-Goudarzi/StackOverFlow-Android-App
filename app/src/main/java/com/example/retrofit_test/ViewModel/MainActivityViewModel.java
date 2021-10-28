package com.example.retrofit_test.ViewModel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.retrofit_test.Model.ApiRepository;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.ModelObject.StackExchangeQuestion;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    private ApiRepository repository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        repository = new ApiRepository();
    }

    public LiveData<List<Question>> getQuestions() {
        repository.getQuestion(new Callback<StackExchangeQuestion>() {
            @Override
            public void onResponse(Call<StackExchangeQuestion> call, Response<StackExchangeQuestion> response) {
                if (!response.isSuccessful())
                    return;
                if (response.body() == null)
                    return;
                questions.setValue(response.body().getQuestions());
            }
            @Override
            public void onFailure(Call<StackExchangeQuestion> call, Throwable t) {

            }
        });
        return questions;
    }


}
