package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libraryapplication.databinding.FragmentProfileBinding;
import com.example.libraryapplication.databinding.FragmentReadBinding;

public class ProfileFragment extends Fragment {

    DBHelper dbHelper;
    EditText userName, userEmail, userPass;
    Button logout;

    public ProfileFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DBHelper(getActivity());

        //username = (EditText) view.

        /*
        if(getArguments() != null){

            Intent getInfo = getActivity().getIntent();
            String username = getInfo.getStringExtra("username");
            String email = getInfo.getStringExtra("email");
            String password = getInfo.getStringExtra("password");


            userName = userName.findViewById(R.id.profusername);
            userName.setText(username);
            //getActionBar().getTitle(username);

            userEmail = userEmail.findViewById(R.id.profemail);
            userEmail.setText(email);

            userPass = userPass.findViewById(R.id.profpassword);
            userPass.setText(password);

        }*/

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
