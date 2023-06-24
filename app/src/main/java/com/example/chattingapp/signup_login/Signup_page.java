package com.example.chattingapp.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chattingapp.R;
import com.example.chattingapp.dashboard.Home_main_screen;

public class Signup_page extends AppCompatActivity {

    LottieAnimationView lottie;

    TextView tv_signup,hhhh;
    Button btn_signup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        tv_signup = findViewById(R.id.tv_alreadyhaveanaccount);
        btn_signup=findViewById(R.id.btn_signup);

        lottie = findViewById(R.id.lottie);
        lottie.playAnimation();

        tv_signup.setOnClickListener(view -> {
            Intent i = new Intent(Signup_page.this,Login_page.class);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            finish();
        });
        btn_signup.setOnClickListener(v -> {
            startActivity(new Intent(Signup_page.this, Home_main_screen.class));
            finish();
        });

    }
}