package com.example.smartketcalci;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.LeadingMarginSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    ScrollView scrollView;
    TextView textViewRecognizedText;
    TextView textViewTotalSum;
    Button btnHistory;
    HistoryManager historyManager;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        scrollView = findViewById(R.id.scrollView);
        textViewRecognizedText = findViewById(R.id.textViewRecognizedText);
        textViewTotalSum = findViewById(R.id.textViewTotalSum);
        btnHistory = findViewById(R.id.btnHistory);

        historyManager = new HistoryManager(this);

        // Receive data from MainActivity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String recognizedText = bundle.getString("recognizedText");
            double totalSum = bundle.getDouble("totalSum");

            // Add ordered list and line breaks
            String formattedText = addOrderedList(recognizedText);

            textViewRecognizedText.setText("Text: \n" +formattedText);
            textViewTotalSum.setText("Total: \n" + totalSum);

            // Add to history
            String historyItem = "Recognized Text: " + formattedText + "\nTotal Sum: \n" + totalSum;
            historyManager.addHistory(historyItem);
        }

        // Set the visibility of the divider line based on the number of lines in the recognized text
        int lineCount = textViewRecognizedText.getLineCount();

        // History button click listener
        btnHistory.setOnClickListener(v -> {
            // Start HistoryActivity
            Intent intent = new Intent(Activity2.this, HistoryActivity.class);
            startActivity(intent);
        });

        // Scroll to the bottom of the text view
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
    }

    private String addOrderedList(String text) {
        StringBuilder builder = new StringBuilder();
        String[] lines = text.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.matches("\\d+\\.\\s+.*")) {
                // Already has an ordered list number, add leading margin
                builder.append(addLeadingMargin(line, 50)).append("\n");
            } else {
                // Add ordered list number and leading margin
                builder.append(addLeadingMargin(i + 1 + ". " + line, 50)).append("\n");
            }
            // Add space of 1px after each line
            builder.append("\n");
        }
        return builder.toString();
    }

    private SpannableString addLeadingMargin(String text, int margin) {
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new LeadingMarginSpan.Standard(margin, 0), 0, spannableString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}












//package com.example.smartketcalci;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//public class Activity2 extends AppCompatActivity {
//    TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_2);
//
//        textView = findViewById(R.id.textView);
//
//        // Receive data from MainActivity
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            String recognizedText = bundle.getString("recognizedText");
//            double totalSum = bundle.getDouble("totalSum");
//
//            textView.setText("Text: " + recognizedText + "\nTotal Sum: " + totalSum);
//        }
//    }
//}

