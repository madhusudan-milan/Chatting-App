package com.example.chattingapp.signup_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chattingapp.Model.ContactModel;
import com.example.chattingapp.R;
import com.example.chattingapp.databinding.ActivitySignupPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup_page extends AppCompatActivity {

    LottieAnimationView lottie;
    ActivitySignupPageBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        progressDialog =new ProgressDialog(Signup_page.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("we Are Creating Your Account");
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.textusername.getText().toString().isEmpty() && !binding.textEmail.getText().toString().isEmpty() && !binding.textPassword.getText().toString().isEmpty())
                {
                    progressDialog.show();
                    mAuth.createUserWithEmailAndPassword(binding.textEmail.getText().toString(),binding.textPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful())
                                    {
                                        ContactModel contactModel = new ContactModel(binding.textusername.getText().toString(),binding.textEmail.getText().toString(),binding.textPassword.getText().toString());
                                        String id=task.getResult().getUser().getUid();
                                        database.getReference().child("Users").child(id).setValue(contactModel);
                                        Toast.makeText(Signup_page.this,"Sign Up Successful",Toast.LENGTH_SHORT).show();

                                    }
                                    else {
                                        Toast.makeText(Signup_page.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(Signup_page.this,"Enter Credentials",Toast.LENGTH_SHORT).show();

                }
            }
        });
        binding.tvAlreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup_page.this,Login_page.class);
                startActivity(intent);
            }
        });

//        setContentView(R.layout.activity_signup_page);
//
//        tv_signup = findViewById(R.id.tv_alreadyhaveanaccount);

//
        lottie = findViewById(R.id.lottie);
        lottie.playAnimation();

//        tv_signup.setOnClickListener(view -> {
//            Intent i = new Intent(Signup_page.this,Login_page.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(i);
//            finish();
//        });
//        btn_signup.setOnClickListener(v -> {
//            startActivity(new Intent(Signup_page.this, Home_main_screen.class));
//            finish();
//        });

    }
}