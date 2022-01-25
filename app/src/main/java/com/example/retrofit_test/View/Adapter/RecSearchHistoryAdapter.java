package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_test.Model.DB.Entity.DiffUtil.SearchComparator;
import com.example.retrofit_test.Model.DB.Entity.Search;
import com.example.retrofit_test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecSearchHistoryAdapter extends RecyclerView.Adapter<RecSearchHistoryAdapter.MyViewHolder> {

    private ArrayList<Search> searchList;

    public RecSearchHistoryAdapter(ArrayList<Search> searchList) {
        this.searchList = searchList;
    }

    public void setSearchList(ArrayList<Search> newSearchList) {
        final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new SearchComparator(searchList,newSearchList));
        searchList.clear();
        searchList.addAll(newSearchList);
        result.dispatchUpdatesTo(RecSearchHistoryAdapter.this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_search_history,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView searchQueryTv;
        RecyclerView.LayoutManager layoutManager;
        ArrayList<String> tags;
        RecQuestionTagAdapter adapter;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            searchQueryTv = itemView.findViewById(R.id.search_query);
            RecyclerView recyclerView = itemView.findViewById(R.id.rec_search_tags);
            layoutManager = new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false);
            tags = new ArrayList<>();
            adapter = new RecQuestionTagAdapter(tags);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        public void bind(int position) {
            searchQueryTv.setText("\" " + searchList.get(position).getQuery() + " \"");
            tags.clear();
            tags.addAll(createTagsArray(searchList.get(position).getTags()));
            adapter.notifyDataSetChanged();
        }
        private List<String> createTagsArray(String tags) {
            return Arrays.asList(tags.split(";"));
        }
    }
}
