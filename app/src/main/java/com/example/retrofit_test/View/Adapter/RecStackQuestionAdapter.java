package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.R;

import java.util.ArrayList;

public class RecStackQuestionAdapter extends RecyclerView.Adapter<RecStackQuestionAdapter.MyViewHolder> {

    private ArrayList<Question> questions;

    public RecStackQuestionAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setQuestions(ArrayList<Question> newQuestions) {
        questions.clear();
        questions.addAll(newQuestions);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_main_stack,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvQuestionId.setText(questions.get(position).getQuestionId());
        holder.tvQuestionTitle.setText(questions.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestionId,tvQuestionTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionId = itemView.findViewById(R.id.question_id);
            tvQuestionTitle = itemView.findViewById(R.id.question_title);
        }
    }
}
