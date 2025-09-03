package com.freetime.appstore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppViewHolder> {

    public interface OnDownloadClickListener {
        void onDownloadClick(AppInfo app);
    }

    private final List<AppInfo> appList;
    private final OnDownloadClickListener downloadClickListener;

    public AppAdapter(List<AppInfo> appList, OnDownloadClickListener listener) {
        this.appList = appList;
        this.downloadClickListener = listener;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new AppViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        AppInfo app = appList.get(position);
        holder.appNameTextView.setText(app.getName());
        holder.downloadButton.setOnClickListener(v -> {
            if (downloadClickListener != null) {
                downloadClickListener.onDownloadClick(app);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    static class AppViewHolder extends RecyclerView.ViewHolder {
        TextView appNameTextView;
        Button downloadButton;

        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            appNameTextView = itemView.findViewById(R.id.appNameTextView);
            downloadButton = itemView.findViewById(R.id.downloadAppButton);
        }
    }
}