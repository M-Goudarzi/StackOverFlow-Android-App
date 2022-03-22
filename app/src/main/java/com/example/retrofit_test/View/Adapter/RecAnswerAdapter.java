package com.example.retrofit_test.View.Adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_test.Common.DateHelper;
import com.example.retrofit_test.Common.MarkdownHelper;
import com.example.retrofit_test.Model.Networking.ModelObject.Answer;
import com.example.retrofit_test.Model.Networking.ModelObject.Comment;
import com.example.retrofit_test.View.Activity.UserProfileActivity;
import com.example.retrofit_test.databinding.ItemRecAnswersBinding;

import java.util.ArrayList;

import io.noties.markwon.Markwon;

public class RecAnswerAdapter extends RecyclerView.Adapter<RecAnswerAdapter.MyHolder> {

    private final ArrayList<Answer> answers;
    private ItemRecAnswersBinding binding;
    private final Markwon markwon;
    private final Drawable defaultUserAvatar;

    public RecAnswerAdapter(ArrayList<Answer> answers, Markwon markwon, Drawable defaultUserAvatar) {
        this.answers = answers;
        this.markwon = markwon;
        this.defaultUserAvatar = defaultUserAvatar;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRecAnswersBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.date.setText(String.format("Answered : %s", DateHelper.getCreationDate(answers.get(position).getCreationDate())));
        if (!answers.get(position).getIsAccepted())
            holder.date.setCompoundDrawables(null,null,null,null);
        markwon.setMarkdown(holder.body,new MarkdownHelper().handleSpecialChars(answers.get(position).getBodyMarkdown()));
        Glide.with(binding.getRoot())
                .load(answers.get(position).getOwner().getProfileImage())
                .placeholder(defaultUserAvatar)
                .into(holder.imageAvatar);
        holder.golden.setText(answers.get(position).getOwner().getBadgeCounts().getGold() + "");
        holder.silver.setText(answers.get(position).getOwner().getBadgeCounts().getSilver() + "");
        holder.bronze.setText(answers.get(position).getOwner().getBadgeCounts().getBronze() + "");
        holder.userName.setText(answers.get(position).getOwner().getDisplayName());
        if (answers.get(position).getComments() != null) {
            holder.recComments.setNestedScrollingEnabled(false);
            holder.recComments.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext(),RecyclerView.VERTICAL,false));
            holder.recComments.setAdapter(new RecCommentAdapter((ArrayList<Comment>) answers.get(position).getComments(),markwon));
        }
        final View.OnClickListener userProfileClickListener = v -> {
            Intent intent = new Intent(binding.getRoot().getContext(), UserProfileActivity.class);
            intent.putExtra("userId",answers.get(position).getOwner().getUserId());
            binding.getRoot().getContext().startActivity(intent);
        };
        holder.imageAvatar.setOnClickListener(userProfileClickListener);
        holder.userName.setOnClickListener(userProfileClickListener);
    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView date,body,userName,golden,silver,bronze;
        ImageView imageAvatar;
        RecyclerView recComments;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            date = binding.tvDateItemAnswers;
            body = binding.tvBodyItemAnswers;
            userName = binding.tvUserNameItemAnswers;
            golden = binding.tvUserGoldenBadgeItemAnswers;
            silver = binding.tvUserSilverBadgeItemAnswers;
            bronze = binding.tvUserBronzeBadgeItemAnswers;
            imageAvatar = binding.ivUserAvatarItemAnswers;
            recComments = binding.recCommentsItemAnswers;
        }
    }

}
