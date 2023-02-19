package com.example.libraryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    SearchView searchView;
    DynamicRVModel dynamicRVModels;
    DynamicRVAdapter dynamicRVAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav);
        bottomNavigationView.setOnItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();


    }
    private final BottomNavigationView.OnItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment=null;
            switch(item.getItemId()){
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;

                case R.id.favorites:
                    selectedFragment = new FavoritesFragment();
                    break;

                case R.id.add:
                    selectedFragment = new ReadFragment();
                    break;

                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;

            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.container,selectedFragment).commit();
            return true;
        }

    };

}