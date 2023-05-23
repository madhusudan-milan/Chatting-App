package com.example.chattingapp.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chattingapp.R;

public class ChatScreen extends AppCompatActivity {
//        ActivityChatBinding binding;
//    FirebaseDatabase database;
//    FirbaseAuth auth;
    ImageView backArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);

        backArrow=findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatScreen.this,ContactList.class);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().hide();
        String userName=getIntent().getStringExtra("userName");
        String profilePic=getIntent().getStringExtra("profilePic");
        //binding.userName,setText(userName); this applied when the firbase is connected
        //binding.backArrow.setOnClickListener(new View.onClickListener());



    }
}