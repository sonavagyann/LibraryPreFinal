package com.example.libraryapplication.userSassion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapplication.Fragments.HomeActivity;
import com.example.libraryapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText email, password;
    Button signinbutton;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView forgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        signinbutton = (Button) findViewById(R.id.signinbutton);
        progressDialog = new ProgressDialog(this);
        forgotPass = (TextView) findViewById(R.id.forgot_pass);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        sharedPreferences = getSharedPreferences("UserPref", Context.MODE_PRIVATE);

        forgotPass.setOnClickListener(view -> {
            Intent i = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            startActivity(i);
        });

        signinbutton.setOnClickListener(view -> {
            String em = email.getText().toString();
            String pass = password.getText().toString();
            PerformLogin();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", em);
            editor.putString("password", pass);
            editor.commit();
        });

    }

    private void PerformLogin() {
        String em = email.getText().toString();
        String pass = password.getText().toString();

        if (em.equals("") || pass.equals("")) {
            Toast.makeText(LoginActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener( this, task -> {
                if (task.isSuccessful()) {
                    if(mAuth.getCurrentUser().isEmailVerified()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        finish();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Please, verify your email", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
