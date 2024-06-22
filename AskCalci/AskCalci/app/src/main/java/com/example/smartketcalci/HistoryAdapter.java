package com.example.smartketcalci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<String> historyList;
    private Context context;
    private HistoryManager historyManager;

    public HistoryAdapter(List<String> historyList, Context context, HistoryManager historyManager) {
        this.historyList = historyList;
        this.context = context;
        this.historyManager = historyManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String historyItem = historyList.get(position);
        holder.textViewHistoryItem.setText(historyItem);

        // Delete button click listener
        holder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewHistoryItem;
        ImageView imageViewDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHistoryItem = itemView.findViewById(R.id.txtHistory);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }

    private void showDeleteConfirmationDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete History")
                .setMessage("Are you sure you want to delete this history?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteHistoryItem(position);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private void deleteHistoryItem(int position) {
        historyList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, historyList.size());
        historyManager.saveHistory(historyList);
        Toast.makeText(context, "History item deleted", Toast.LENGTH_SHORT).show();
    }
}




//package com.example.smartketcalci;
//// HistoryAdapter.java
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
//    private List<String> historyList;
//
//    public HistoryAdapter(List<String> historyList) {
//        this.historyList = historyList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        String historyItem = historyList.get(position);
//        holder.txtHistory.setText(historyItem);
//    }
//
//    @Override
//    public int getItemCount() {
//        return historyList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView txtHistory;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            txtHistory = itemView.findViewById(R.id.txtHistory);
//        }
//    }
//}
