package com.example.retrofit_test.Model.DB.Entity.DiffUtil;

import androidx.recyclerview.widget.DiffUtil;

import com.example.retrofit_test.Model.DB.Entity.Search;

import java.util.ArrayList;

public class SearchComparator extends DiffUtil.Callback {

    ArrayList<Search> oldList,newList;

    public SearchComparator(ArrayList<Search> oldList, ArrayList<Search> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        if (oldList != null)
            return oldList.size();
        return 0;
    }

    @Override
    public int getNewListSize() {
        if (newList != null)
            return newList.size();
        return 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getuId() == newList.get(newItemPosition).getuId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
