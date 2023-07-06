package com.example.chattingapp.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.chattingapp.Adapter.FragmentAdapter;
import com.example.chattingapp.GroupChatActivity;
import com.example.chattingapp.Model.ContactModel;
import com.example.chattingapp.R;
import com.example.chattingapp.databinding.ActivityHomeMainScreenBinding;
import com.example.chattingapp.profile.Profile_page;
import com.example.chattingapp.signup_login.Login_page;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home_Main_Screen extends AppCompatActivity {
    ActivityHomeMainScreenBinding binding;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseUsers;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeMainScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.tollbar);




        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseUsers = database.getReference("Users");
        String id= mAuth.getCurrentUser().getUid();
        DatabaseReference userName= databaseUsers.child(id).child("userName");

        ContactModel contactModel=new ContactModel();
        contactModel.setUserId(contactModel.getUserName());

        userName.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.getValue().toString();
                binding.tvUserName.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        binding.profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home_Main_Screen.this, Profile_page.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectedId = item.getItemId();

        switch (selectedId)
        {

            case R.id.groupchat:
                Intent intent1 =new Intent(Home_Main_Screen.this, GroupChatActivity.class);
                startActivity(intent1);
                break;
            case R.id.logout:
                mAuth.signOut();
                Intent intent=new Intent(Home_Main_Screen.this, Login_page.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}