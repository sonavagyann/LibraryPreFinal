package com.example.libraryapplication;

import android.content.Intent;
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

    private TextView username, email, password;
    Button logout;

    public ProfileFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        username = username.findViewById(R.id.profusername);
        email = email.findViewById(R.id.profemail);
        password = password.findViewById(R.id.profpassword);

        String user = getActivity().getIntent().getStringExtra("name");
        String em = getActivity().getIntent().getStringExtra("email");
        String pass = getActivity().getIntent().getStringExtra("password");

        username.setText(user);
        email.setText(em);
        password.setText(pass);*/
        //if(getArguments() != null){}
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

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
