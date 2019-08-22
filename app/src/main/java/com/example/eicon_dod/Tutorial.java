package com.example.eicon_dod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Tutorial extends AppCompatActivity {

    Button btn;
    ImageView imgView;
    int i= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        btn = findViewById(R.id.end_btn);
        imgView = findViewById(R.id.end_img);

        btn.setOnClickListener(view -> {
            ++i;

            switch (i){
                case 1: imgView.setImageResource(R.drawable.to1);
                break;
                case 2: imgView.setImageResource(R.drawable.to6);
                break;
                case 3: imgView.setImageResource(R.drawable.to2);
                break;
                case 4 :imgView.setImageResource(R.drawable.to3);
                break;
                case 5: imgView.setImageResource(R.drawable.to5);
                    finish();
                break;
            }
        });
    }
}
