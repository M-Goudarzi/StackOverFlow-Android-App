package com.example.StackOverFlow_App.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.RecyclerView;

import com.example.StackOverFlow_App.databinding.LoadStateLayoutBinding;

public class LoadStateAdapter extends androidx.paging.LoadStateAdapter<LoadStateAdapter.ViewHolder> {

    private final View.OnClickListener mRetryCallback;
    private LoadStateLayoutBinding binding;

    public LoadStateAdapter(View.OnClickListener mRetryCallback) {
        this.mRetryCallback = mRetryCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @NonNull LoadState loadState) {
        viewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, @NonNull LoadState loadState) {
        binding = LoadStateLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
        View view = binding.getRoot();
        return new ViewHolder(view, mRetryCallback);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ProgressBar mProgressBar;
        private final TextView mErrorMsg;
        private final Button mRetry;

        public ViewHolder(@NonNull View itemView,View.OnClickListener retryCallBack) {
            super(itemView);
            mProgressBar = binding.progressBar;
            mErrorMsg = binding.errorMsg;
            mRetry = binding.retryButton;
            mRetry.setOnClickListener(retryCallBack);
        }

        private void bind(LoadState loadState) {
            // Check load state
            if (loadState instanceof LoadState.Error) {
                // Get the error
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                // Set text of Error message
                mErrorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }
            // set visibility of widgets based on LoadState
            mProgressBar.setVisibility(loadState instanceof LoadState.Loading
                    ? View.VISIBLE : View.GONE);
            mRetry.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);
            mErrorMsg.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);

        }
    }
}
