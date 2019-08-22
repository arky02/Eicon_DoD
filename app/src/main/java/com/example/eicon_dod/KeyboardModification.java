package com.example.eicon_dod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.eicon_dod.customkeyboard.ImePreferences;
import com.example.eicon_dod.customkeyboard.LatinKeyboard;
import com.example.eicon_dod.customkeyboard.SoftKeyboard;

public class KeyboardModification extends AppCompatActivity  {

    SeekBar seekBar;
    Integer finalResult = null;
    TextView textView;
    Button btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyboard_modification);

        Intent mintent = new Intent(getApplicationContext(), ImePreferences.class);
        startActivity(mintent);

//        seekBar = (SeekBar) findViewById(R.id.seekBar);
//        textView = (TextView) findViewById(R.id.textView);
//
//        seekBar.setMax(60); //40~100
//
//        btn_setting = findViewById(R.id.btn_setting);
//        btn_setting.setOnClickListener(view -> {
//            Intent mintent = new Intent(getApplicationContext(), ImePreferences.class);
//            startActivity(mintent);
//
//        });
//
//    }
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//        if (progress % 10 == 0) {
//            textView.setText(String.valueOf(progress));
//            finalResult = progress;
//
//        } else {
//            seekBar.setProgress((progress / 10) * 20);
//            textView.setText(String.valueOf((progress / 10) * 20));
//            finalResult = (progress / 10) * 10;
//        }
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        if(finalResult !=null){
//            SoftKeyboard.mQwertyKeyboard.setKeyHeight(finalResult+40);
////                    float b= getResources().getDimension(a);
//        }
//
    }
}
