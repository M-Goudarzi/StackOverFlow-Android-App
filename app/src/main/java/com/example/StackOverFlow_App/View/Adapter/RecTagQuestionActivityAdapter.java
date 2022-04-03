package com.example.StackOverFlow_App.View.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StackOverFlow_App.View.Activity.SearchResultActivity;
import com.example.StackOverFlow_App.databinding.ItemRecTagsQuestionActivityBinding;
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

        holder.chip.setOnClickListener(v -> {
            Intent intent = new Intent(holder.chip.getContext(), SearchResultActivity.class);
            intent.putExtra("searchOnlyOneTag",true);
            intent.putExtra("searchTags",holder.chip.getText());
            holder.chip.getContext().startActivity(intent);
        });

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
