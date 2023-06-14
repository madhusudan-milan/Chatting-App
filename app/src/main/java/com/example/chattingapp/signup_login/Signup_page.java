package com.example.chattingapp.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chattingapp.R;

public class Signup_page extends AppCompatActivity {

    LottieAnimationView lottie;

    TextView signup,hhhh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        signup = findViewById(R.id.tv_alreadyhaveanaccount);

        lottie = findViewById(R.id.lottie);
        lottie.playAnimation();

        signup.setOnClickListener(view -> {
            Intent i = new Intent(Signup_page.this,Login_page.class);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            finish();
        });


    }
}