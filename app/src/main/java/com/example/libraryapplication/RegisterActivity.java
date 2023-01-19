package com.example.libraryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, repassword;
    Button regbutton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        regbutton = (Button) findViewById(R.id.regbutton);
        DB = new DBHelper(this);

        regbutton.setOnClickListener(view -> {

            String user = username.getText().toString();
            String em = email.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            //so that every field is filled
            if(user.equals("") || em.equals("") || pass.equals("")) {
                Toast.makeText(RegisterActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
            }
            else{
                //if user is not registered insert his info
                if(pass.equals(repass)){
                    boolean checkuser = DB.checkuser(user);
                    if(!checkuser){
                        boolean insert = DB.DataInsert(user, em, pass);
                        //if everything goes well we get registered
                        if(insert){
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                //password and repass do not match
                else{
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}