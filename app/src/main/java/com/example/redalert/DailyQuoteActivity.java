package com.example.redalert;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DailyQuoteActivity extends AppCompatActivity {

    TextView quoteText, authorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quote);

        quoteText = findViewById(R.id.quoteText);
        authorText = findViewById(R.id.authorText);

        fetchQuoteFromAPI();
    }

    private void fetchQuoteFromAPI() {
        new Thread(() -> {
            try {
                URL url = new URL("https://api.quotable.io/random");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                JSONObject json = new JSONObject(result.toString());
                String content = json.getString("content");
                String author = json.getString("author");

                new Handler(Looper.getMainLooper()).post(() -> {
                    quoteText.setText("💬 " + content);
                    authorText.setText("~ " + author);
                });

                reader.close();
                connection.disconnect();

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> {
                    quoteText.setText("❌ Failed to load quote.");
                    authorText.setText("");
                });
                e.printStackTrace();
            }
        }).start();
    }
}
