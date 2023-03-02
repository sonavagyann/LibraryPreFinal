package com.example.libraryapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {


    TextView userN, userE, userP;
    Button logout;

    public ProfileFragment(){

    }

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
        String username = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");

        userE.setText(email);
        userP.setText(password);

        logout = view.findViewById(R.id.logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LogoutActivity.class);
                startActivity(intent);
            }
        });



        return view;



    }

}
