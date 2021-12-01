package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.QuestionsDiffCallback;
import com.example.retrofit_test.R;
import java.util.ArrayList;

public class RecHomeQuestionAdapter extends RecyclerView.Adapter<RecHomeQuestionAdapter.MyViewHolder> {

    private final ArrayList<Question> questions;

    public RecHomeQuestionAdapter(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setQuestions(ArrayList<Question> newQuestions) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new QuestionsDiffCallback(questions,newQuestions));
        questions.clear();
        questions.addAll(newQuestions);
        result.dispatchUpdatesTo(RecHomeQuestionAdapter.this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_home_questions,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvQuestionTitle.setText(questions.get(position).getTitle());

        Glide.with(holder.itemView)
                .load(questions.get(position).getOwner().getProfileImage())
                .placeholder(R.drawable.account_circle_white_24)
                .into(holder.ivAvatar);

        holder.tags.clear();
        holder.tags.addAll(questions.get(position).getTags());
        holder.adapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestionTitle;
        ImageView ivAvatar;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        ArrayList<String> tags;
        RecHomeTagAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionTitle = itemView.findViewById(R.id.question_title);
            ivAvatar = itemView.findViewById(R.id.question_avatar);
            recyclerView = itemView.findViewById(R.id.rec_home_tags);
            layoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            tags = new ArrayList<>();
            adapter = new RecHomeTagAdapter(tags);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}