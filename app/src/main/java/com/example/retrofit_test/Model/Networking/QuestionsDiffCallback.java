package com.example.retrofit_test.Model.Networking;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.retrofit_test.Model.Networking.ModelObject.Question;

import java.util.ArrayList;

public class QuestionsDiffCallback extends DiffUtil.Callback {

    ArrayList<Question> oldQuestions;
    ArrayList<Question> newQuestions;

    public QuestionsDiffCallback(ArrayList<Question> oldQuestions, ArrayList<Question> newQuestions) {
        this.oldQuestions = oldQuestions;
        this.newQuestions = newQuestions;
    }

    @Override
    public int getOldListSize() {
        if (oldQuestions != null)
            return oldQuestions.size();
        return 0;
    }

    @Override
    public int getNewListSize() {
        if (newQuestions != null)
            return newQuestions.size();
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldQuestions.get(oldItemPosition).getQuestionId().equals(newQuestions.get(newItemPosition).getQuestionId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldQuestions.get(oldItemPosition).equals(newQuestions.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
