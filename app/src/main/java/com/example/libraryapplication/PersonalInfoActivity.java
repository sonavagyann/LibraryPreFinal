package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalInfoActivity extends AppCompatActivity {

    private TextView username, email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        username = findViewById(R.id.profusername);
        email = findViewById(R.id.profemail);
        password = findViewById(R.id.profpassword);

        Intent intent = getIntent();
        String user = intent.getStringExtra("name");
        String em = intent.getStringExtra("email");
        String pass = intent.getStringExtra("password");

        username.setText(user);
        email.setText(em);
        password.setText(pass);
    }
}
