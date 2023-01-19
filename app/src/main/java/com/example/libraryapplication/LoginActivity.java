package com.example.libraryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    EditText username, email, password;
    Button signinbutton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        username = (EditText) findViewById(R.id.username1);
        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        signinbutton = (Button) findViewById(R.id.signinbutton);
        DB = new DBHelper(this);

        signinbutton.setOnClickListener(v -> {

            String user = username.getText().toString();
            String em = email.getText().toString();
            String pass = password.getText().toString();

            //if at least one string is empty
            if(user.equals("") || email.equals("") || pass.equals("")) {
                Toast.makeText(LoginActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
            }
            else{
                boolean checkuseremailpass = DB.checkuseremailpass(user, em, pass);
                //if such user exists
                if(checkuseremailpass){
                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Some inputs do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
