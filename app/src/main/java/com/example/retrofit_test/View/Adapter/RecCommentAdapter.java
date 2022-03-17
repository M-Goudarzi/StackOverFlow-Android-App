package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_test.Model.Networking.ModelObject.Comment;
import com.example.retrofit_test.databinding.ItemRecCommentsBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.noties.markwon.Markwon;

public class RecCommentAdapter extends RecyclerView.Adapter<RecCommentAdapter.myHolder> {

    private ArrayList<Comment> comments;
    private ItemRecCommentsBinding binding;
    private Map<String,String> specialChars;
    private final Markwon markwon;

    public RecCommentAdapter(ArrayList<Comment> comments, Markwon markwon) {
        this.comments = comments;
        fillUpTheSpecialCharsHashMap();
        this.markwon = markwon;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecCommentsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
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

        private TextView userName,body;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            userName = binding.tvUserNameItemComments;
            body = binding.tvBodyItemComment;
        }

        void bind(int position) {
            userName.setText("@" + comments.get(position).owner.getDisplayName() + " : ");
            markwon.setMarkdown(body,handleSpecialChars(comments.get(position).getBodyMarkdown()));
        }

    }

    private void fillUpTheSpecialCharsHashMap() {
        specialChars = new HashMap<>();
        specialChars.put("&lt;","<");
        specialChars.put("&gt;",">");
        specialChars.put("&quot;","\"");
        specialChars.put("&nbsp;"," ");
        specialChars.put("&amp;","&");
        specialChars.put("&apos;","'");
        specialChars.put("&#39;","'");
        specialChars.put("&#40;","(");
        specialChars.put("&#41;",")");
        specialChars.put("&#215;","x");
    }

    private String handleSpecialChars(String text) {
        Set set=specialChars.entrySet();//Converting to Set so that we can traverse
        for (Object o : set) {
            //Converting to Map.Entry so that we can get key and value separately
            Map.Entry entry = (Map.Entry) o;
            text = text.replace(entry.getKey().toString(),entry.getValue().toString());
        }
        return text;
    }

}
