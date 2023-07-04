package com.example.chattingapp.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chattingapp.R;

public class profile_page extends AppCompatActivity {

    TextView edit_profile,hhhh;
    TextView username;
    TextView about;
    TextView Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        edit_profile = findViewById(R.id.edit_profile);
        username = findViewById(R.id.Username);
        about = findViewById(R.id.About);
        Email = findViewById(R.id.email);

        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            String About = intent.getStringExtra("About");
            String email = intent.getStringExtra("Email");

            // Display the saved profile information
            username.setText(name);
            about.setText(About);
            Email.setText(email);

        }

        edit_profile.setOnClickListener(view -> {
            Intent i = new Intent(profile_page.this, profile_edit.class);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            finish();
        });
    }
}