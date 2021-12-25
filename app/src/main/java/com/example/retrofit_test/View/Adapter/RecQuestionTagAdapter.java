package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_test.R;

import java.util.ArrayList;

class RecQuestionTagAdapter extends RecyclerView.Adapter<RecQuestionTagAdapter.MyHolder> {

    ArrayList<String> tags;

    public RecQuestionTagAdapter(ArrayList<String> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_question_tags,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tvTag.setText(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

        TextView tvTag;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = itemView.findViewById(R.id.tv_item_rec_home_tags);
        }
    }
}
