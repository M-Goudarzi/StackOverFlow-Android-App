package com.example.StackOverFlow_App.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StackOverFlow_App.databinding.ItemRecTagsBinding;

import java.util.ArrayList;

class RecQuestionTagAdapter extends RecyclerView.Adapter<RecQuestionTagAdapter.MyHolder> {

    private final ArrayList<String> tags;
    private ItemRecTagsBinding binding;

    public RecQuestionTagAdapter(ArrayList<String> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecTagsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
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

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView tvTag;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvTag = binding.tvItemRecHomeTags;
        }
    }
}
