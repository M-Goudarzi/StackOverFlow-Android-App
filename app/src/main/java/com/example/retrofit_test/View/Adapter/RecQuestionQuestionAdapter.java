package com.example.retrofit_test.View.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.Model.Networking.QuestionsDiffCallback;
import com.example.retrofit_test.R;
import java.util.ArrayList;

import io.noties.markwon.Markwon;
import io.noties.markwon.SpanFactory;

public class RecQuestionQuestionAdapter extends RecyclerView.Adapter<RecQuestionQuestionAdapter.MyViewHolder> {

    private final ArrayList<Question> questions;
    private final Markwon markwon;

    public RecQuestionQuestionAdapter(ArrayList<Question> questions, Context context) {
        this.questions = questions;
        markwon = Markwon.create(context);
    }

    public void setQuestions(ArrayList<Question> newQuestions) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new QuestionsDiffCallback(questions,newQuestions));
        questions.clear();
        questions.addAll(newQuestions);
        result.dispatchUpdatesTo(RecQuestionQuestionAdapter.this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_question_questions,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (questions.get(position).getBountyAmount() != null) {
            String str =  "**+" + questions.get(position).getBountyAmount() + "** " + questions.get(position).getTitle();
            markwon.setMarkdown(holder.tvQuestionTitle, str);
        }
        else {
            //   holder.tvQuestionTitle.setText(questions.get(position).getTitle());
            markwon.setMarkdown(holder.tvQuestionTitle,questions.get(position).getTitle());
        }


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
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        ArrayList<String> tags;
        RecQuestionTagAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionTitle = itemView.findViewById(R.id.question_title);
            tvQuestionVotes = itemView.findViewById(R.id.tv_question_info_votes);
            tvQuestionAnswers = itemView.findViewById(R.id.tv_question_info_answers);
            tvQuestionViews = itemView.findViewById(R.id.tv_question_info_views);
            recyclerView = itemView.findViewById(R.id.rec_home_tags);
            layoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            tags = new ArrayList<>();
            adapter = new RecQuestionTagAdapter(tags);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }
}
