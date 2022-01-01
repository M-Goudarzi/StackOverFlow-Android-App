package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit_test.Model.Networking.ModelObject.Question;
import com.example.retrofit_test.R;
import java.util.ArrayList;
import androidx.paging.PagingDataAdapter;
import io.noties.markwon.Markwon;

public class RecQuestionQuestionAdapter extends PagingDataAdapter<Question,RecQuestionQuestionAdapter.MyViewHolder> {

    private final Markwon markwon;

    public RecQuestionQuestionAdapter(@NonNull DiffUtil.ItemCallback<Question> diffCallback, Markwon markwon) {
        super(diffCallback);
        this.markwon = markwon;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_question_questions,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Question question = getItem(position);
        if (question != null)
            holder.bind(question);
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

        private void bind(Question question) {
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
        }

    }
}
