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

import com.example.libraryapplication.userSassion.LogoutActivity;

public class ProfileFragment extends Fragment {

    TextView userE, userP;
    Button logout;

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
        logout = view.findViewById(R.id.logout_button);
        logout.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), LogoutActivity.class);
            startActivity(intent);
        });

        return view;
    }
}

/*
<ImageView
        android:id="@+id/book_cover"
        android:layout_width="120dp"
        android:layout_height="150dp"
        android:src="@drawable/hunger_games_cover"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.12" />

    <TextView
        android:id="@+id/textView6"
        android:layout_width="70dp"
        android:layout_height="30dp"
        android:layout_marginTop="32dp"
        android:text="Title:"
        android:textColor="@color/black"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toEndOf="@+id/book_cover"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.059" />

    <TextView
        android:id="@+id/book_title"
        android:layout_width="218dp"
        android:layout_height="42dp"
        android:layout_marginEnd="10dp"
        android:gravity="left"
        android:paddingStart="10dp"
        android:text="The Hunger Games"
        android:textColor="@color/black"
        android:textSize="15sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toEndOf="@+id/textView6"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.101" />

    <TextView
        android:id="@+id/textView8"
        android:layout_width="70dp"
        android:layout_height="30dp"
        android:layout_marginTop="32dp"
        android:text="Author:"
        android:gravity="left|center_vertical"
        android:textColor="@color/black"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toEndOf="@+id/book_cover"
        app:layout_constraintTop_toBottomOf="@+id/textView6"
        app:layout_constraintVertical_bias="0.0" />

    <TextView
        android:id="@+id/book_author"
        android:layout_width="219dp"
        android:layout_height="30dp"
        android:layout_marginTop="23dp"
        android:layout_marginEnd="10dp"
        android:gravity="left|center"
        android:paddingStart="10dp"
        android:text="Suzanne Collins"
        android:textColor="@color/black"
        android:textSize="15sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toEndOf="@+id/textView8"
        app:layout_constraintTop_toBottomOf="@+id/book_title" />

    <TextView
        android:id="@+id/textView10"
        android:layout_width="70dp"
        android:layout_height="30dp"
        android:layout_marginBottom="68dp"
        android:text="Pages:"
        android:gravity="left|center_vertical"
        android:textColor="@color/black"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toEndOf="@+id/book_cover"
        app:layout_constraintTop_toBottomOf="@+id/textView8"
        app:layout_constraintVertical_bias="0.057" />

    <TextView
        android:id="@+id/book_pages"
        android:layout_width="217dp"
        android:layout_height="29dp"
        android:layout_marginTop="27dp"
        android:gravity="left|center_vertical"
        android:paddingStart="10dp"
        android:text="384"
        android:textColor="@color/black"
        android:textSize="15sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toEndOf="@+id/textView10"
        app:layout_constraintTop_toBottomOf="@+id/book_author" />

    <TextView
        android:id="@+id/book_description"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:gravity="center_vertical"
        android:paddingStart="10dp"
        android:paddingEnd="10dp"
        android:text="In what was once North America,
the Capitol of Panem maintains its hold on its 12 districts
by forcing them each to select a boy and a girl, called Tributes,
to compete in a nationally televised event called the Hunger Games.
Every citizen must watch as the youths fight to the death until only
one remains. District 12 Tribute Katniss Everdeen (Jennifer Lawrence)
has little to rely on, other than her hunting skills and sharp instincts,
in an arena where she must weigh survival against love."
        android:textColor="@color/black"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.55" />
 */
