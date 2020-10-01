package com.example.gradapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText mName, mEmail, mPassword, mconfirm_password,mLoginBtn;
    Button mRegisterBtn;
    //TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.Name);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mconfirm_password = findViewById(R.id.confirmPassword);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

       // if(fAuth.getCurrentUser() != null){
       //     startActivity(new Intent(getApplicationContext(),MainActivity.class));
       //     finish();
      //  }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String confirmPassword = mconfirm_password.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                   mEmail.setError("Email is Required!!! ");
                   return;
                }
                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is required !!!");
                    return;
                }
                if(TextUtils.isEmpty(confirmPassword))
                {
                    mPassword.setError("Please confirm your Password!!!");
                    return;
                }
                if(password.length() < 6)
                {
                    mPassword.setError("Password must be >= 6 characters ");
                    return;
                }
                if(!password.equals(confirmPassword))
                {
                    mPassword.setError("Passwords do not match !!!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else{
                            Toast.makeText(Register.this,"Error!!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}