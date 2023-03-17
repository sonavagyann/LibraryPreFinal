package com.example.libraryapplication.userSassion;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libraryapplication.Adapters.Book;
import com.example.libraryapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, repassword;
    Button regbutton;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        regbutton = (Button) findViewById(R.id.regbutton);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        regbutton.setOnClickListener(view -> PerformAuth());
    }

    private void PerformAuth() {

        String em = email.getText().toString();
        String pass = password.getText().toString();
        String repass = regbutton.getText().toString();

        if(em.equals("") || pass.equals("")) {
            Toast.makeText(RegisterActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
        }
        else if(pass.equals(repass)){
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
        mAuth.createUserWithEmailAndPassword(em, pass).addOnCompleteListener( this, task -> {
            if (task.isSuccessful()) {
                mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "Verify your email", Toast.LENGTH_SHORT).show();
                        Map<String, List<Book>> data = new HashMap<>();
                        FirebaseFirestore.getInstance().collection("Users").document(mAuth.getCurrentUser().getUid()).set(data);
                        sendUserToNextActivity();
                        //finish();
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, "" + task1.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}