package com.example.chattingapp.signup_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chattingapp.R;
import com.example.chattingapp.dashboard.Home_Main_Screen;
import com.example.chattingapp.databinding.ActivityLoginPageBinding;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login_page extends AppCompatActivity {
  ActivityLoginPageBinding binding;
  ProgressDialog progressDialog;
  FirebaseAuth mAuth;
  FirebaseDatabase firebaseDatabase;
    LottieAnimationView lottie;

    TextView login,hhhh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         binding=ActivityLoginPageBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

         mAuth = FirebaseAuth.getInstance();
         firebaseDatabase = FirebaseDatabase.getInstance();

         progressDialog = new ProgressDialog(Login_page.this);
         progressDialog.setTitle("Login");
         progressDialog.setMessage("Please Wait!\nValidation in Progress");

         binding.btnLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 if (!binding.textEmail.getText().toString().isEmpty() && !binding.textPassword.getText().toString().isEmpty())
                 {
                     progressDialog.show();
                     mAuth.signInWithEmailAndPassword(binding.textEmail.getText().toString(),binding.textPassword.getText().toString())
                             .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful())
                                    {
                                        Intent intent=new Intent(Login_page.this, Home_Main_Screen.class);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(Login_page.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();;

                                    }
                                 }
                             });
                 }
                 else {
                     Toast.makeText(Login_page.this,"Enter Credentials",Toast.LENGTH_SHORT).show();


                 }
             }
         });
         if (mAuth.getCurrentUser()!=null)
         {
             Intent intent =new Intent(Login_page.this, Home_Main_Screen.class);
             startActivity(intent);
         }


         binding.tvDonthaveaccount.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(Login_page.this,Signup_page.class);
                 startActivity(intent);
             }
         });



        lottie = findViewById(R.id.lottie);

        lottie.playAnimation();

//        login.setOnClickListener(view -> {
//            Intent i = new Intent(Login_page.this,Signup_page.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//            startActivity(i);
//            finish();
//        });
    }
}