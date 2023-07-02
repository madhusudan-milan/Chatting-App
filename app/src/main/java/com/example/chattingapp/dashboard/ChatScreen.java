package com.example.chattingapp.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chattingapp.Adapter.ChatAdapter;
import com.example.chattingapp.Model.MessageModel;
import com.example.chattingapp.R;
import com.example.chattingapp.databinding.ActivityChatScreenBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatScreen extends AppCompatActivity {
    ActivityChatScreenBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityChatScreenBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());

       database =FirebaseDatabase.getInstance();
       mAuth=FirebaseAuth.getInstance();

       final  String senderId= mAuth.getUid();

       String reciverId = getIntent().getStringExtra("userId");
       String userName = getIntent().getStringExtra("userName");
       String profilePic = getIntent().getStringExtra("profiePic");

       binding.contactName.setText(userName);
       Picasso.get().load(profilePic).placeholder(R.drawable.avatar).into(binding.profilePic);




        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChatScreen.this,Home_Main_Screen.class);
                startActivity(intent);
                finish();
            }
        });

        final ArrayList<MessageModel> messageModels = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(messageModels,this,reciverId);
        binding.chatRecycler.setAdapter(chatAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecycler.setLayoutManager(layoutManager);

        final  String senderRoom = senderId + reciverId;//identify which one is sender or reciver
        final String reviverRoom = reciverId + senderId;

        database.getReference().child("chats")//creating new node in firebase
                        .child(senderRoom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        messageModels.clear();
                                        for (DataSnapshot snapshot1 : snapshot.getChildren())
                                        {
                                            MessageModel model = snapshot1.getValue(MessageModel.class);
                                            model.setMessageId(snapshot1.getKey());
                                            messageModels.add(model);

                                        }
                                        chatAdapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

        binding.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.enterMessage.getText().toString();//this will store the message in a string
                final  MessageModel model = new MessageModel(senderId,message);
                model.setTimeStamp(new Date().getTime());//put the time on the message bottom
                binding.enterMessage.setText("");

                database.getReference().child("chats")//create another for Storage of messages
                        .child(senderRoom)
                        .push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                database.getReference().child("chats")
                                        .child(reviverRoom)
                                        .push()
                                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }
                        });
            }
        });




    }
}