package com.example.chattingapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chattingapp.R;

import java.text.BreakIterator;

public class profile_edit extends AppCompatActivity {

    ImageView back_arrow,hhhh;
    EditText username;
    EditText about;
    EditText Email;


    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        back_arrow = findViewById(R.id.back_Arrow);
        btnSave = findViewById(R.id.btn_save);
        username = findViewById(R.id.Username);
        about = findViewById(R.id.About);
        Email = findViewById(R.id.email);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String About = about.getText().toString();
                String email = Email.getText().toString();

                Intent intent = new Intent(profile_edit.this, profile_page.class);
                intent.putExtra("name", name);
                intent.putExtra("About",About);
                intent.putExtra("Email",email);

                // intent.putExtra("number",number);

                startActivity(intent);


                back_arrow.setOnClickListener(view -> {
                    Intent i = new Intent(profile_edit.this, profile_page.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                    finish();
                });
            }
        });
    }
}