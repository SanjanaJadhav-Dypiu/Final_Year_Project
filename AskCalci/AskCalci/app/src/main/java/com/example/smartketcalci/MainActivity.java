package com.example.smartketcalci;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageView startListeningImg;
    private SpeechRecognizer speechRecognizer;
    private Button btnJumpToHistory;
    private Button btnOpenCalculator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startListeningImg = findViewById(R.id.imageView);
        btnJumpToHistory = findViewById(R.id.btnJumpToHistory);
        btnOpenCalculator = findViewById(R.id.btnOpenCalculator);


        startListeningImg.setOnClickListener(v -> startListening());
        btnJumpToHistory.setOnClickListener(v -> jumpToHistory());
        btnOpenCalculator.setOnClickListener(v -> openCalculator());



        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                Toast.makeText(MainActivity.this, "Listening...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int error) {
                Toast.makeText(MainActivity.this, "Error " + error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String recognizedText = matches.get(0);
                    processRecognizedText(recognizedText);
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
            }
        });
    }

    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        speechRecognizer.startListening(intent);
    }



    private void processRecognizedText(String recognizedText) {
        double totalSum = 0.0;
        StringBuilder formattedText = new StringBuilder();

        // Process recognized text here to calculate the total sum

        // For demonstration purposes, let's assume we're just summing numbers.
        // Replace this with your custom logic to extract numbers and perform calculations.
        String[] words = recognizedText.split("\\s+");
        for (String word : words) {
            try {
                double num = Double.parseDouble(word);
                totalSum += num;
                formattedText.append(word).append("\n");
            } catch (NumberFormatException ignored) {
                formattedText.append(word).append(" ");
            }
        }

        // Launch Activity2 and pass the formatted recognized text and total sum
        Intent intent = new Intent(MainActivity.this, Activity2.class);
        intent.putExtra("recognizedText", formattedText.toString());
        intent.putExtra("totalSum", totalSum);
        startActivity(intent);
    }

    private void jumpToHistory() {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }

    private void openCalculator() {
        {
            Intent intent = new Intent(MainActivity.this, SimpleCalculator.class);
            startActivity(intent);
        }
    }

}



//package com.example.smartketcalci;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.speech.RecognitionListener;
//import android.speech.RecognizerIntent;
//import android.speech.SpeechRecognizer;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.Locale;
//
//public class MainActivity extends AppCompatActivity {
//
//    private ImageView startListeningImg;
//    private SpeechRecognizer speechRecognizer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        startListeningImg = findViewById(R.id.imageView);
//        startListeningImg.setOnClickListener(v -> startListening());
//
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
//        speechRecognizer.setRecognitionListener(new RecognitionListener() {
//            @Override
//            public void onReadyForSpeech(Bundle params) {
//                Toast.makeText(MainActivity.this, "Listening...", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onBeginningOfSpeech() {
//            }
//
//            @Override
//            public void onRmsChanged(float rmsdB) {
//            }
//
//            @Override
//            public void onBufferReceived(byte[] buffer) {
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//            }
//
//            @Override
//            public void onError(int error) {
//                Toast.makeText(MainActivity.this, "Error " + error, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResults(Bundle results) {
//                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//                if (matches != null && !matches.isEmpty()) {
//                    String recognizedText = matches.get(0);
//                    processRecognizedText(recognizedText);
//                }
//            }
//
//            @Override
//            public void onPartialResults(Bundle partialResults) {
//            }
//
//            @Override
//            public void onEvent(int eventType, Bundle params) {
//            }
//        });
//    }
//
//    private void startListening() {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
//        speechRecognizer.startListening(intent);
//    }
//
//    private void processRecognizedText(String recognizedText) {
//        double totalSum = 0.0;
//        StringBuilder formattedText = new StringBuilder();
//
//        // Process recognized text here to calculate the total sum
//
//        // For demonstration purposes, let's assume we're just summing numbers.
//        // Replace this with your custom logic to extract numbers and perform calculations.
//        String[] words = recognizedText.split("\\s+");
//        for (String word : words) {
//            try {
//                double num = Double.parseDouble(word);
//                totalSum += num;
//                formattedText.append(word).append("\n");
//            } catch (NumberFormatException ignored) {
//                formattedText.append(word).append(" ");
//            }
//        }
//
//        // Launch Activity2 and pass the formatted recognized text and total sum
//        Intent intent = new Intent(MainActivity.this, Activity2.class);
//        intent.putExtra("recognizedText", formattedText.toString());
//        intent.putExtra("totalSum", totalSum);
//        startActivity(intent);
//    }
//
//}


