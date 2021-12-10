package com.example.retrofit_test.View.Adapter;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
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

        if (questions.get(position).getBountyAmount() != null) {
            String str = "+" + questions.get(position).getBountyAmount() + " " + questions.get(position).getTitle();
            Spannable spannable = new SpannableString(str);
            spannable.setSpan(new ForegroundColorSpan(Color.BLUE),0,questions.get(position).getBountyAmount().toString().length()+1, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            holder.tvQuestionTitle.setText(spannable,TextView.BufferType.SPANNABLE);
        }
        else
            holder.tvQuestionTitle.setText(questions.get(position).getTitle());

        holder.tvQuestionVotes.setText(holder.itemView.getContext().getString(R.string.QuestionVotes,questions.get(position).getUpVoteCount()));
        holder.tvQuestionAnswers.setText(holder.itemView.getContext().getString(R.string.QuestionAnswers,questions.get(position).getAnswerCount()));
        holder.tvQuestionViews.setText(holder.itemView.getContext().getString(R.string.QuestionViews,questions.get(position).getViewCount()));

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
        TextView tvQuestionVotes,tvQuestionAnswers,tvQuestionViews;
    //    ImageView ivAvatar;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        ArrayList<String> tags;
        RecHomeTagAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionTitle = itemView.findViewById(R.id.question_title);
            tvQuestionVotes = itemView.findViewById(R.id.tv_question_info_votes);
            tvQuestionAnswers = itemView.findViewById(R.id.tv_question_info_answers);
            tvQuestionViews = itemView.findViewById(R.id.tv_question_info_views);
        //    ivAvatar = itemView.findViewById(R.id.question_avatar);
            recyclerView = itemView.findViewById(R.id.rec_home_tags);
            layoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            tags = new ArrayList<>();
            adapter = new RecHomeTagAdapter(tags);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
