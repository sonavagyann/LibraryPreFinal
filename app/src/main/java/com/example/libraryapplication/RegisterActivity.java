package com.example.libraryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, password, repassword;
    Button regbutton;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        //mAuth = FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        regbutton = (Button) findViewById(R.id.regbutton);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });

    }

    private void PerformAuth() {

        String name = username.getText().toString();
        String em = email.getText().toString();
        String pass = password.getText().toString();
        String repass = regbutton.getText().toString();


        if(name.equals("") || em.equals("") || pass.equals("")) {
            Toast.makeText(RegisterActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
        }
        //else if (!em.matches(emailPattern)) {
            //Toast.makeText(this, "Enter proper email", Toast.LENGTH_SHORT).show();
        //}
        else if(pass.equals(repass)){
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
    }
            mAuth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener( this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    progressDialog.dismiss();
                                    sendUserToNextActivity();
                                    Toast.makeText(RegisterActivity.this, "Verify your email", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    progressDialog.dismiss();
                                    Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        //progressDialog.dismiss();
                        //sendUserToNextActivity();
                        //Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private void sendUserToNextActivity() {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

}