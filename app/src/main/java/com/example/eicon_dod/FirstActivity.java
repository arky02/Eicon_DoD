package com.example.eicon_dod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    Button btnfirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnfirst = findViewById(R.id.btnfirst);

        btnfirst.setOnClickListener(view -> {
            Intent mn = new Intent(getApplicationContext(),SpeechBubble_checkpermission.class);
            startActivity(mn);
            finish();
        });
    }
}
