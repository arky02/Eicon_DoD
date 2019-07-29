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

    Button btn_save,btn_profile;
    EditText edt_name,edt_quote,edt_birth;
    ImageView img_profile;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        btn_save = findViewById(R.id.btn_save);
        img_profile = findViewById(R.id.e_profile);
        edt_birth = findViewById(R.id.e_edt_birth);
        edt_name = findViewById(R.id.e_edt_name);
        edt_quote = findViewById(R.id.e_edt_quote);
        btn_profile = findViewById(R.id.btn_editprofile);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getApplicationContext().getSharedPreferences("shared_profile",MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(edt_name.getText().toString(),"name");
                edit.putString(edt_quote.getText().toString(),"quote");
                edit.putString(edt_birth.getText().toString(),"birth");
                edit.commit();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
