package com.example.chattingapp.signup_login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chattingapp.R;

public class Login_page extends AppCompatActivity {

    LottieAnimationView lottie;

    TextView login,hhhh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.tv_donthaveaccount);
        lottie = findViewById(R.id.lottie);

        lottie.playAnimation();

        login.setOnClickListener(view -> {
            Intent i = new Intent(Login_page.this,Signup_page.class);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(i);
            finish();
        });
    }
}