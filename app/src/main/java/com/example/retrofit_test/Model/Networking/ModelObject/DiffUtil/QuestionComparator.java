package com.example.retrofit_test.Model.Networking.ModelObject.DiffUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.retrofit_test.Model.Networking.ModelObject.Question;

public class QuestionComparator extends DiffUtil.ItemCallback<Question> {

    @Override
    public boolean areItemsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
        return oldItem.getQuestionId().equals(newItem.getQuestionId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Question oldItem, @NonNull Question newItem) {
        return oldItem.equals(newItem);
    }
}
