package com.example.retrofit_test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_test.ModelObject.Item;

import java.util.ArrayList;

class RecStackQuestionAdapter extends RecyclerView.Adapter<RecStackQuestionAdapter.MyViewHolder> {

    private ArrayList<Item> questions;

    public RecStackQuestionAdapter(ArrayList<Item> questions) {
        this.questions = questions;
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
