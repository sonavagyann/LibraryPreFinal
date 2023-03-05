package com.example.libraryapplication.userSassion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libraryapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText resetEmail;
    private String email;
    private Button resetButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        mAuth = FirebaseAuth.getInstance();
        resetEmail = findViewById(R.id.resetEmail);
        resetButton = findViewById(R.id.resetBtn);

        resetButton.setOnClickListener(view -> resetInfo());
    }

    private void resetInfo(){
        email = resetEmail.getText().toString();
        if(email.isEmpty()){
            Toast.makeText(this, "Enter your email", Toast.LENGTH_SHORT).show();
        }
        else{
            forgotPass();
        }
    }

    private void forgotPass(){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(ResetPasswordActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                finish();
            }
            else{
                Toast.makeText(ResetPasswordActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
