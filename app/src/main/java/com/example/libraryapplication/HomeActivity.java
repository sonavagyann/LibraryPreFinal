package com.example.libraryapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

//searchbar isn't fully done
public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //or try OnItemSelectedListener
        BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav);
        bottomNavigationView.setOnItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();

        /*ArrayList<StaticRVModel> item = new ArrayList<>();
        item.add(new StaticRVModel("Fiction"));
        item.add(new StaticRVModel("Poetry"));
        item.add(new StaticRVModel("Novel"));
        item.add(new StaticRVModel("History"));
        item.add(new StaticRVModel("Drama"));
        item.add(new StaticRVModel("Fantasy"));
        item.add(new StaticRVModel("Detective"));
        item.add(new StaticRVModel("Spiritual"));

        //private SearchView searchBar;
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = findViewById(R.id.recyclerView);
        StaticRVAdapter staticRVAdapter = new StaticRVAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRVAdapter);*/
    }

    //or try OnItemSelectedListener
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

                case R.id.read:
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
