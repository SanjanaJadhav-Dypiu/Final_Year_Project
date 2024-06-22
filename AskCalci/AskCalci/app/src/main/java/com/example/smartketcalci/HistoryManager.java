package com.example.smartketcalci;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HistoryManager {
    private static final String PREF_NAME = "HistoryPrefs";
    private static final String KEY_HISTORY = "historyList";

    private SharedPreferences preferences;

    public HistoryManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void addHistory(String historyItem) {
        List<String> historyList = getHistory();
        historyList.add(historyItem);
        saveHistory(historyList);
    }

    public List<String> getHistory() {
        List<String> historyList = new ArrayList<>(preferences.getStringSet(KEY_HISTORY, new TreeSet<>()));
        Collections.reverse(historyList); // Reverse the list to display in chronological order
        return historyList;
    }

    void saveHistory(List<String> historyList) {
        Set<String> set = new TreeSet<>(historyList);
        preferences.edit().putStringSet(KEY_HISTORY, set).apply();
    }
}



//package com.example.smartketcalci;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;
//
//public class HistoryManager {
//    private static final String PREF_NAME = "HistoryPrefs";
//    private static final String KEY_HISTORY = "historyList";
//
//    private SharedPreferences preferences;
//
//    public HistoryManager(Context context) {
//        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//    }
//
//    public void addHistory(String historyItem) {
//        List<String> historyList = getHistory();
//        historyList.add(historyItem);
//        saveHistory(historyList);
//    }
//
//    public List<String> getHistory() {
//        Set<String> defaultSet = new TreeSet<>(Collections.reverseOrder()); // Use TreeSet to store items in reverse order
//        return new ArrayList<>(preferences.getStringSet(KEY_HISTORY, defaultSet));
//    }
//
//    void saveHistory(List<String> historyList) {
//        Set<String> set = new TreeSet<>(Collections.reverseOrder()); // Use TreeSet to store items in reverse order
//        set.addAll(historyList);
//        preferences.edit().putStringSet(KEY_HISTORY, set).apply();
//    }
//}
