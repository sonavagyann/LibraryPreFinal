package com.example.libraryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    EditText email, password;
    Button signinbutton;

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);


        email = (EditText) findViewById(R.id.email1);
        password = (EditText) findViewById(R.id.password1);
        signinbutton = (Button) findViewById(R.id.signinbutton);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        sharedPreferences = getSharedPreferences("UserPref", Context.MODE_PRIVATE);

        signinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pass = password.getText().toString();

                PerformLogin();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("email", em);
                editor.putString("password", pass);
                editor.commit();
            }
        });
    }

    private void PerformLogin() {

        String em = email.getText().toString();
        String pass = password.getText().toString();


        if (em.equals("") || pass.equals("")) {
            Toast.makeText(LoginActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
        //} else if (!em.matches(emailPattern)) {
            //Toast.makeText(this, "Enter proper email", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(em, pass).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        if(mAuth.getCurrentUser().isEmailVerified()){
                            progressDialog.dismiss();
                            sendUserToNextActivity();
                            Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Please, verify your email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
