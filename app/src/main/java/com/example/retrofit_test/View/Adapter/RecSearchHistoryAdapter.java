package com.example.retrofit_test.View.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
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
import com.example.retrofit_test.View.SearchResultActivity;
import com.example.retrofit_test.databinding.ItemRecSearchHistoryBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecSearchHistoryAdapter extends RecyclerView.Adapter<RecSearchHistoryAdapter.MyViewHolder> {

    private final ArrayList<Search> searchList;
    private final Activity activity;
    private ItemRecSearchHistoryBinding binding;

    public RecSearchHistoryAdapter(ArrayList<Search> searchList, Activity activity) {
        this.searchList = searchList;
        this.activity = activity;
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
        binding = ItemRecSearchHistoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
        binding.constraintItemSearchHistory.setOnClickListener(v -> {
            Intent intent = new Intent(activity, SearchResultActivity.class);
            intent.putExtra("searchQuery",searchList.get(position).query);
            intent.putExtra("searchTags",searchList.get(position).tags);
            intent.putExtra("searchIsAcceptedBool",searchList.get(position).hasAccepted);
            intent.putExtra("searchIsClosedBool",searchList.get(position).closed);
            intent.putExtra("searchNumberOfAnswers",searchList.get(position).minimumAnswers);
            intent.putExtra("searchTitleContains",searchList.get(position).titleContains);
            intent.putExtra("searchBodyContains",searchList.get(position).bodyContains);
            activity.startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        });
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
            searchQueryTv = binding.searchQuery;
            RecyclerView recyclerView = binding.recSearchTags;
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
