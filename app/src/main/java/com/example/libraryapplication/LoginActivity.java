package com.example.libraryapplication;



import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapplication.databinding.ActivityMainBinding;


public class LoginActivity extends AppCompatActivity {

    //ActivityMainBinding binding;

    EditText username, email, password;
    Button signinbutton;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
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

            sendInfo();
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

                    //sendInfo();

                }
                else{
                    Toast.makeText(LoginActivity.this, "Some inputs do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void sendInfo() {

        Intent in;
        in= new Intent(LoginActivity.this, LogoutActivity.class);
        in.putExtra("username", String.valueOf(username));
        in.putExtra("email", String.valueOf(email));
        in.putExtra("password", String.valueOf(password));
        startActivity(in);

    }

}
