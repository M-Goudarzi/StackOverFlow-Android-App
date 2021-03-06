package com.example.StackOverFlow_App.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StackOverFlow_App.Other.QuestionClickListener;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Question;
import com.example.StackOverFlow_App.R;
import com.example.StackOverFlow_App.databinding.ItemRecQuestionBinding;

import java.util.ArrayList;

import androidx.paging.PagingDataAdapter;
import io.noties.markwon.Markwon;

public class RecQuestionPagingAdapter extends PagingDataAdapter<Question, RecQuestionPagingAdapter.MyViewHolder> {

    private final Markwon markwon;
    private ItemRecQuestionBinding binding;
    private final QuestionClickListener questionClicklistener;

    public RecQuestionPagingAdapter(@NonNull DiffUtil.ItemCallback<Question> diffCallback, Markwon markwon, QuestionClickListener questionClicklistener) {
        super(diffCallback);
        this.markwon = markwon;
        this.questionClicklistener = questionClicklistener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecQuestionBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(getItem(position),questionClicklistener);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestionTitle;
        TextView tvQuestionVotes,tvQuestionAnswers,tvQuestionViews;
        RecyclerView recyclerView;
        RecyclerView.LayoutManager layoutManager;
        ArrayList<String> tags;
        RecQuestionTagAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestionTitle = binding.questionTitle;
            tvQuestionVotes = binding.tvQuestionInfoVotes;
            tvQuestionAnswers = binding.tvQuestionInfoAnswers;
            tvQuestionViews = binding.tvQuestionInfoViews;
            recyclerView = binding.recQuestionTags;
            layoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            tags = new ArrayList<>();
            adapter = new RecQuestionTagAdapter(tags);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        private void bind(Question question, QuestionClickListener questionClicklistener) {
                if (question.getBountyAmount() != null) {
                    String str =  "**+" + question.getBountyAmount() + "** " + question.getTitle();
                    markwon.setMarkdown(tvQuestionTitle, str);
                }
                else {
                    markwon.setMarkdown(tvQuestionTitle,question.getTitle());
                }


                tvQuestionVotes.setText(itemView.getContext().getString(R.string.QuestionVotes,question.getUpVoteCount()));
                tvQuestionAnswers.setText(itemView.getContext().getString(R.string.QuestionAnswers,question.getAnswerCount()));
                tvQuestionViews.setText(itemView.getContext().getString(R.string.QuestionViews,question.getViewCount()));

                tags.clear();
                tags.addAll(question.getTags());
                adapter.notifyDataSetChanged();

            final View.OnClickListener onClickListener = view -> questionClicklistener.onClick(question);

            tvQuestionTitle.setOnClickListener(onClickListener);

        }

    }
}
