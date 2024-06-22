package com.example.smartketcalci;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    Button btnGoBack;
    HistoryManager historyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerViewHistory);
        btnGoBack = findViewById(R.id.btnGoBack);
        historyManager = new HistoryManager(this);

        // Retrieve historyList from HistoryManager
        List<String> historyList = historyManager.getHistory();

        // Initialize and set up RecyclerView
        historyAdapter = new HistoryAdapter(historyList, this, historyManager);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(historyAdapter);

        // Go Back button click listener
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}




//package com.example.smartketcalci;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import java.util.List;
//
//public class HistoryActivity extends AppCompatActivity {
//    RecyclerView recyclerView;
//    HistoryAdapter historyAdapter;
//    Button btnGoBack;
//    HistoryManager historyManager;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_history);
//
//        recyclerView = findViewById(R.id.recyclerViewHistory);
//        btnGoBack = findViewById(R.id.btnGoBack);
//        historyManager = new HistoryManager(this);
//
//        // Retrieve historyList from HistoryManager
//        List<String> historyList = historyManager.getHistory();
//
//        // Initialize and set up RecyclerView
//        // Initialize and set up RecyclerView
//        historyAdapter = new HistoryAdapter(historyList, this, historyManager);
//
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(historyAdapter);
//
//        // Go Back button click listener
//        btnGoBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//}
