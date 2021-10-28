package com.example.retrofit_test.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.R;
import com.example.retrofit_test.View.Adapter.RecStackQuestionAdapter;
import com.example.retrofit_test.ViewModel.MainActivityViewModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecStackQuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        viewModel.getQuestions().observe(this,questions -> {
            adapter.setQuestions((ArrayList<Question>) questions);
        });

    }

    void init() {
        RecyclerView recyclerView = findViewById(R.id.rec_main_questions);
        ArrayList<Question> questions = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecStackQuestionAdapter(questions);
        recyclerView.setAdapter(adapter);
    }

}