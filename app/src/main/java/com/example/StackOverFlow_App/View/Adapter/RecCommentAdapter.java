package com.example.StackOverFlow_App.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StackOverFlow_App.Other.MarkdownHelper;
import com.example.StackOverFlow_App.Model.Networking.ModelObject.Comment;
import com.example.StackOverFlow_App.databinding.ItemRecCommentsBinding;

import java.util.ArrayList;

import io.noties.markwon.Markwon;

public class RecCommentAdapter extends RecyclerView.Adapter<RecCommentAdapter.myHolder> {

    private final ArrayList<Comment> comments;
    private ItemRecCommentsBinding binding;
    private final Markwon markwon;

    public RecCommentAdapter(ArrayList<Comment> comments, Markwon markwon) {
        this.comments = comments;
        this.markwon = markwon;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecCommentsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        View view = binding.getRoot();
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {

        private final TextView userName, body;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            userName = binding.tvUserNameItemComments;
            body = binding.tvBodyItemComment;
        }

        void bind(int position) {
            userName.setText("@" + comments.get(position).owner.getDisplayName() + " : ");
            markwon.setMarkdown(body, new MarkdownHelper().handleSpecialChars(comments.get(position).getBodyMarkdown()));
        }

    }
}
