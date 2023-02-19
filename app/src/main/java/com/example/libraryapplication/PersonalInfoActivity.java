package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;


public class PersonalInfoActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.libraryapplication.MESSAGE";


    private TextView username, email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        //username = findViewById(R.id.profusername);
        //email = findViewById(R.id.profemail);
        //password = findViewById(R.id.profpassword);

        //Intent intent = getIntent();
        //String user = intent.getStringExtra("name");
        //String em = intent.getStringExtra("email");
        //String pass = intent.getStringExtra("password");

        //username.setText(user);
        //email.setText(em);
        //password.setText(pass);

        Intent intent = getIntent();
        String message = intent.getStringExtra(RegisterActivity.EXTRA_MESSAGE);
        TextView getUser = (TextView) findViewById(R.id.profusername);
        getUser.setText(message);

        Intent intent2 = getIntent();
        String message2 = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView getEmail = (TextView) findViewById(R.id.profemail);
        getEmail.setText(message2);

        Intent intent3 = getIntent();
        String message3 = intent2.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView getPass = (TextView) findViewById(R.id.profpassword);
        getPass.setText(message3);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
}



