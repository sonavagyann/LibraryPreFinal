package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;


public class PersonalInfoActivity extends AppCompatActivity {


    TextView userName, userEmail, userPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);


        Intent getInfo = getIntent();
        String username = getInfo.getStringExtra("username");
        String email = getInfo.getStringExtra("email");
        String password = getInfo.getStringExtra("password");


        userName = findViewById(R.id.profusername);
        userName.setText(username);
        //getActionBar().getTitle(username);

        userEmail = findViewById(R.id.profemail);
        userEmail.setText(email);

        userPass = findViewById(R.id.profpassword);
        userPass.setText(password);




    }
}



