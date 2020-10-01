package com.example.gradapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button mFirebaseBtn;
    private Button mFirebaseBtn1;
    public Button mFirebaseBtn2;
    private DatabaseReference mDatabase;
    private Button mNotificationbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseBtn = (Button) findViewById(R.id.drivetocurbbtn);
        mFirebaseBtn1 = (Button) findViewById(R.id.returnhomebtn);
        mFirebaseBtn2 = (Button) findViewById(R.id.Trashlevelstatusbtn);

        mNotificationbtn = findViewById(R.id.Notificationbtn);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //final String DriveToCurb_Flag = "0";
        mDatabase.child("drive/drive_flag").setValue(Integer.parseInt("0"));
        mDatabase.child("return/return_flag").setValue(Integer.parseInt("0"));
       // String ReturnToHome_Flag = "0";

        mFirebaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1- Create child in the root object
                // 2- Assign some value to the child object
                //int DriveToCurb_Flag = 0;
               // int ReturnToHome_Flag = 0;

                   mDatabase.child("drive/drive_flag").setValue(Integer.parseInt("1"));
               // mDatabase.child("DriveToCurb_Flag").child("ReturnToHome_Flag").setValue("1");

                  // mDatabase.child("ReturnToHome_Flag").setValue("1");

            }
        });

        mFirebaseBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1- Create child in the root object
                // 2- Assign some value to the child object
                //int DriveToCurb_Flag = 0;
                // int ReturnToHome_Flag = 0;

              //  mDatabase.child("DriveToCurb_Flag").setValue("1");
                // mDatabase.child("DriveToCurb_Flag").child("ReturnToHome_Flag").setValue("1");

                 mDatabase.child("return/return_flag").setValue(Integer.parseInt("1"));

            }
        });

        mNotificationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Notification.class));
            }
        });


      //  mFirebaseBtn1.setOnClickListener(view );
        // Write a message to the database
       // FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference myRef = database.getReference("message");

       // myRef.setValue("Hello, World!");
    }

    // It logs out the user and takes back to the login page
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
       finish();

    }


}