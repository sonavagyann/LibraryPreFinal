package com.example.libraryapplication;

import static androidx.recyclerview.widget.RecyclerView.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.libraryapplication.DRVinterface.LoadMore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeFeedActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaticRVAdapter staticRVAdapter;
    ArrayList<StaticRVModel> staticRVModel;
    //private LinearLayoutManager linearLayoutManager;


    List<DynamicRVModel> items = new ArrayList();
    DynamicRVAdapter dynamicRVAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        staticRVModel = new ArrayList<>();

        ArrayList<StaticRVModel> item = new ArrayList<>();
        item.add(new StaticRVModel("Fiction"));
        item.add(new StaticRVModel("Poetry"));
        item.add(new StaticRVModel("Novel"));
        item.add(new StaticRVModel("History"));
        item.add(new StaticRVModel("Drama"));
        item.add(new StaticRVModel("Fantasy"));
        item.add(new StaticRVModel("Detective"));
        item.add(new StaticRVModel("Spiritual"));


        recyclerView.setAdapter(staticRVAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView = findViewById(R.id.recyclerView);
        staticRVAdapter = new StaticRVAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRVAdapter);
        staticRVAdapter.notifyDataSetChanged();


        items.add(new DynamicRVModel("The Hunger Games"));
        items.add(new DynamicRVModel("Catching Fire"));
        items.add(new DynamicRVModel("The Book Thief"));
        items.add(new DynamicRVModel("Harry Potter and the Prisoner of Azkaban"));
        items.add(new DynamicRVModel("Harry Potter and the Half-Blood Prince"));
        items.add(new DynamicRVModel("Call us what we carry"));
        items.add(new DynamicRVModel("The Forty Days of Musa Dagh"));
        items.add(new DynamicRVModel("Murder of Roger Ackroyd"));


        RecyclerView drv = findViewById(R.id.recyclerView2);
        drv.setLayoutManager(new LinearLayoutManager(this));
        dynamicRVAdapter = new DynamicRVAdapter(drv, this, items);
        drv.setAdapter(dynamicRVAdapter);

        dynamicRVAdapter.setLoadMore(() -> {
            if (items.size() <= 10) {
                items.add(null);
                dynamicRVAdapter.notifyItemInserted(items.size() - 1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        items.remove(items.size() - 1);
                        dynamicRVAdapter.notifyItemRemoved(items.size());

                        int index = items.size();
                        int end = index + 10;
                        for (int i = index; i < end; i++) {
                            String name = UUID.randomUUID().toString();
                            DynamicRVModel item = new DynamicRVModel(name);
                            items.add(item);
                        }
                        dynamicRVAdapter.notifyDataSetChanged();
                        dynamicRVAdapter.setLoaded();
                    }
                }, 3000);
            } else
                Toast.makeText(HomeFeedActivity.this, "Complete", Toast.LENGTH_SHORT).show();
        });

    }
}
