package com.example.retrofit_test.View.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit_test.R;

public class LoadStateAdapter extends androidx.paging.LoadStateAdapter<LoadStateAdapter.ViewHolder> {

    private final View.OnClickListener mRetryCallback;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.load_state_layout,viewGroup,false);
        return new ViewHolder(view, mRetryCallback);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ProgressBar mProgressBar;
        private final TextView mErrorMsg;
        private final Button mRetry;

        public ViewHolder(@NonNull View itemView,View.OnClickListener retryCallBack) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progressBar);
            mErrorMsg = itemView.findViewById(R.id.errorMsg);
            mRetry = itemView.findViewById(R.id.retryButton);
            mRetry.setOnClickListener(retryCallBack);
        }

        private void bind(LoadState loadState) {
            if (loadState instanceof LoadState.Error) {
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                mErrorMsg.setText(loadStateError.getError().getLocalizedMessage());
                mRetry.setVisibility(View.VISIBLE);
                mErrorMsg.setVisibility(View.VISIBLE);
            }

            else if (loadState instanceof LoadState.Loading) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            else if (loadState instanceof LoadState.NotLoading) {
                mProgressBar.setVisibility(View.GONE);
            }
        }
    }
}
