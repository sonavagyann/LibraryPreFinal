package com.example.libraryapplication;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

                    sendNames();
                    sendNames2();
                    sendNames3();

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

    /*
    public void sendInfo() {

        Intent intent = new Intent(this, LogoutActivity.class);
        EditText username = (EditText) findViewById(R.id.username1);
        String userName = username.getText().toString();
        intent.putExtra("username", userName);
        startActivity(intent);

        Intent intent2 = new Intent(this, LogoutActivity.class);
        EditText email = (EditText) findViewById(R.id.email1);
        String userEmail = email.getText().toString();
        intent2.putExtra("email", userEmail);
        startActivity(intent2);

        Intent intent3 = new Intent(this, LogoutActivity.class);
        EditText password = (EditText) findViewById(R.id.password1);
        String userPass = password.getText().toString();
        intent2.putExtra("password", userEmail);
        startActivity(intent3);
    }*/

    public void sendNames() {

        Intent intent = new Intent(this, LogoutActivity.class);
        EditText username = (EditText) findViewById(R.id.username1);
        String message = username.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void sendNames2(){

        Intent intent2 = new Intent(this, LogoutActivity.class);
        EditText email = (EditText) findViewById(R.id.email1);
        String message2 = email.getText().toString();
        intent2.putExtra(EXTRA_MESSAGE, message2);
        startActivity(intent2);
    }

    public void sendNames3(){

        Intent intent3 = new Intent(this, LogoutActivity.class);
        EditText password = (EditText) findViewById(R.id.password1);
        String message3 = password.getText().toString();
        intent3.putExtra(EXTRA_MESSAGE, message3);
        startActivity(intent3);
    }
}
