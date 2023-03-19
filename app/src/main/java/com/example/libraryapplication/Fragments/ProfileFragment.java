package com.example.libraryapplication.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libraryapplication.R;
import com.example.libraryapplication.userSassion.LogoutActivity;

public class ProfileFragment extends Fragment {

    TextView userE, userP;
    Button logout;

    ImageView seePass;

    private boolean isShowPassword = false;

    public ProfileFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userE = view.findViewById(R.id.profemail);
        userP = view.findViewById(R.id.profpassword);
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        userE.setText(email);
        userP.setText(password);
        userP.setTransformationMethod(new PasswordTransformationMethod());
        logout = view.findViewById(R.id.logout_button);
        logout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), LogoutActivity.class);
            startActivity(intent);
        });
        seePass = view.findViewById(R.id.forPass);
        seePass.setImageResource(R.drawable.see_pass);

        seePass.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                    if (isShowPassword) {
                        userP.setTransformationMethod(new PasswordTransformationMethod());
                        seePass.setImageDrawable(getResources().getDrawable(R.drawable.hide_pass));
                        isShowPassword = false;
                    }
                    else{
                        userP.setTransformationMethod(null);
                        seePass.setImageDrawable(getResources().getDrawable(R.drawable.see_pass));
                        isShowPassword = true;
                    }
            }
        });

        return view;
    }
}
