package com.example.retrofit_test.View.Adapter;

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
import com.example.retrofit_test.Model.Networking.ModelObject.Answer;
import com.example.retrofit_test.Model.Networking.ModelObject.Comment;
import com.example.retrofit_test.databinding.ItemRecAnswersBinding;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.noties.markwon.Markwon;

public class RecAnswerAdapter extends RecyclerView.Adapter<RecAnswerAdapter.MyHolder> {

    private final ArrayList<Answer> answers;
    private ItemRecAnswersBinding binding;
    private Map<String,String> specialChars;
    private final Markwon markwon;
    private final Drawable defaultUserAvatar;

    public RecAnswerAdapter(ArrayList<Answer> answers, Markwon markwon, Drawable defaultUserAvatar) {
        this.answers = answers;
        fillUpTheSpecialCharsHashMap();
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
        holder.date.setText(String.format("Answered : %s", getCreationTime(answers.get(position).getCreationDate())));
        if (!answers.get(position).getIsAccepted())
            holder.date.setCompoundDrawables(null,null,null,null);
        markwon.setMarkdown(holder.body,handleSpecialChars(answers.get(position).getBodyMarkdown()));
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

    private String getCreationTime(Integer creationDate) {
        LocalDateTime localDateTime = LocalDateTime.of(1970,1,1,0,0,0);
        LocalDateTime localDateTime1 = localDateTime.plusSeconds(creationDate);
        LocalDateTime now = LocalDateTime.now();
        if (now.getYear() == localDateTime1.getYear()) {
            if (now.getMonth() == localDateTime1.getMonth()) {
                if (now.getDayOfMonth() == localDateTime1.getDayOfMonth()) {
                    return "Today";
                }
                else {
                    return (now.getDayOfMonth() - localDateTime1.getDayOfMonth()) + " Days ago";
                }
            }
            else {
                return (now.getMonth().getValue() - localDateTime1.getMonth().getValue()) + " Months ago";
            }
        }
        else {
            return (now.getYear() - localDateTime1.getYear()) + " Years ago";
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
