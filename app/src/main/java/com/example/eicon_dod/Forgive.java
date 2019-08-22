package com.example.eicon_dod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class Forgive extends AppCompatActivity {

    Spinner spinner;
    Button btn_forgive;
    EditText edttext;
    String selectedQuote;
    Integer goodPoint = 0, sharedGoodPoint;
    SharedPreferences shared;
    TextView textview1, textview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgive);

        spinner = findViewById(R.id.spinner);
        btn_forgive = findViewById(R.id.btn_forgive);
        edttext = findViewById(R.id.forgive_edt);
        textview1 = findViewById(R.id.txt1);

        String quoteList[] = {"A word of kindness is better than a fat pie", "A soft answer turneth away wrath.", "Discretion is the greater part of valor.", "The right word is always a power, and communicates its definiteness to our action.", "Words are soldiers of fortune, hired by different ideas.", "By words we learn thoughts, and by thoughts we learn life.", "For me, words are a form of action, capable of influencing change. Their articulation represents a complete, lived experience", "Good words are worth much, and cost little"};
        ArrayAdapter listadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quoteList);
        spinner.setAdapter(listadapter);

        SharedPreferences sharedBad = getSharedPreferences("badPoint", 0);
        Integer badAmount = (sharedBad.getInt("badPoint", 0)) / 5;
        textview1.setText(badAmount.toString());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedQuote = quoteList[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_forgive.setOnClickListener(view -> {

            if (edttext.getText().toString().equals(selectedQuote)) {

                edttext.setText("");
                shared = getApplicationContext().getSharedPreferences("goodPoint", MODE_PRIVATE);
                ++goodPoint;
                sharedGoodPoint = shared.getInt("goodPoint", 0);

                SharedPreferences.Editor edit = shared.edit();
                edit.putInt("goodPoint", sharedGoodPoint + goodPoint);
                edit.commit();
                textview1.setText(badAmount-goodPoint);



            }

        });

    }


}
