package com.example.libraryapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//not ready
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    /*List<StaticRVModel> staticRVModel;
    StaticRVAdapter staticRVAdapter;*/

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);

        /*staticRVModel = new ArrayList<>();

        staticRVModel.add(new StaticRVModel("Fiction"));
        staticRVModel.add(new StaticRVModel("Poetry"));
        staticRVModel.add(new StaticRVModel("Novel"));
        staticRVModel.add(new StaticRVModel("History"));
        staticRVModel.add(new StaticRVModel("Drama"));
        staticRVModel.add(new StaticRVModel("Fantasy"));
        staticRVModel.add(new StaticRVModel("Detective"));
        staticRVModel.add(new StaticRVModel("Spiritual"));


        staticRVAdapter = new StaticRVAdapter((List) getActivity(), (Context) staticRVModel);
        recyclerView.setAdapter(staticRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);*/

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeFeedActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}

/*<activity
            android:name=".HomeFeedActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>

        </activity>

 */
