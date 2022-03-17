package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_test.databinding.ItemRecTagsBinding;
import com.example.retrofit_test.databinding.ItemRecTagsQuestionActivityBinding;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class RecTagQuestionActivityAdapter extends RecyclerView.Adapter<RecTagQuestionActivityAdapter.MyHolder> {

    private final ArrayList<String> tags;
    private ItemRecTagsQuestionActivityBinding binding;

    public RecTagQuestionActivityAdapter(ArrayList<String> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecTagsQuestionActivityBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.chip.setText(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        Chip chip;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            chip = binding.chipTagItemRecQuestionActivity;
        }
    }
}
