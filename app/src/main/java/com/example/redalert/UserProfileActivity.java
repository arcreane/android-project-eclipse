package com.example.redalert;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfileActivity extends AppCompatActivity {

    EditText inputName, inputAge, inputCycle;
    Button btnSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        inputName = findViewById(R.id.inputName);
        inputAge = findViewById(R.id.inputAge);
        inputCycle = findViewById(R.id.inputCycle);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);

        // 🔄 Load previously saved data
        SharedPreferences prefs = getSharedPreferences("UserProfile", MODE_PRIVATE);
        inputName.setText(prefs.getString("name", ""));
        inputAge.setText(prefs.getString("age", ""));
        inputCycle.setText(prefs.getString("cycle", ""));

        btnSaveProfile.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", inputName.getText().toString());
            editor.putString("age", inputAge.getText().toString());
            editor.putString("cycle", inputCycle.getText().toString());
            editor.apply();

            Toast.makeText(this, "✅ Profile saved successfully!", Toast.LENGTH_SHORT).show();
        });
    }
}
