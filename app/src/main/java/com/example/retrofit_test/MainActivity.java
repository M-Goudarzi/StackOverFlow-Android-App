package com.example.retrofit_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.retrofit_test.ModelObject.Item;
import com.example.retrofit_test.ModelObject.StackExchangeQuestion;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Item> questions;
    RecyclerView.LayoutManager layoutManager;
    RecStackQuestionAdapter adapter;

    StackExchangeApi stackExchangeApi;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.stackexchange.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stackExchangeApi = retrofit.create(StackExchangeApi.class);

        Call<StackExchangeQuestion> call = stackExchangeApi.getQuestions();

        call.enqueue(new Callback<StackExchangeQuestion>() {
            @Override
            public void onResponse(Call<StackExchangeQuestion> call, Response<StackExchangeQuestion> response) {

                if (!response.isSuccessful()) {
                    Log.d("MainActivity Retrofit", "onResponse: " + response.message());
                    return;
                }

                StackExchangeQuestion responseBody = response.body();
                questions.addAll(responseBody.getItems());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<StackExchangeQuestion> call, Throwable t) {
                Log.d("MainActivity Retrofit", "onFailure: " + t.getMessage());
            }
        });

    }

    void init() {
        recyclerView = findViewById(R.id.rec_main_questions);
        questions = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecStackQuestionAdapter(questions);
        recyclerView.setAdapter(adapter);
    }

}