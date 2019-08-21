package com.example.eicon_dod;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Profile extends AppCompatActivity {

    Button btn_save, btn_profile;
    EditText edt_name, edt_quote, edt_birth;
    ImageView img_profile;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        sharedPreferences = getApplicationContext().getSharedPreferences("shared_profile", MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "John Doe");
        String quote = sharedPreferences.getString("quote", "Per aspera ad astra");
        String birth = sharedPreferences.getString("birth", "01/01/1990");

        btn_save = findViewById(R.id.btn_save);
        img_profile = findViewById(R.id.e_profile);
        edt_birth = findViewById(R.id.e_edt_birth);
        edt_name = findViewById(R.id.e_edt_name);
        edt_quote = findViewById(R.id.e_edt_quote);
        btn_profile = findViewById(R.id.btn_editprofile);

        edt_name.setText(name);
        edt_quote.setText(quote);
        edt_birth.setText(birth);

        btn_save.setOnClickListener(
                (View v) -> {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("name", edt_name.getText().toString());
                    edit.putString("quote", edt_quote.getText().toString());
                    edit.putString("birth", edt_birth.getText().toString());
                    edit.apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
        );

    }
}
