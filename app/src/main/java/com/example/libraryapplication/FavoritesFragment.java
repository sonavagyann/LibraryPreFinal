package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    ArrayList<DynamicRVModel> dynamicRVModels = new ArrayList<>();
    DynamicRVAdapter dynamicRVAdapter;
    private RecyclerView favRecyclerView;


    //private HomeFeedActivity homeFeedActivity;
    public FavoritesFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites,container,false);


        /*
        favRecyclerView = view.findViewById(R.id.fav_recyclerview);
        DynamicRVAdapter dynamicRVAdapter= new DynamicRVAdapter(getContext(), dynamicRVModels, (RVInterface) this);
        dynamicRVAdapter.notifyDataSetChanged();
        favRecyclerView.setAdapter(dynamicRVAdapter);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        */


        /*
        Intent intent = getActivity().getIntent();
        if (intent.hasExtra("item")) {
            DynamicRVModel item = intent.getExtra("item");
            dynamicRVModels.add(item);
            dynamicRVAdapter.notifyDataSetChanged();
        }
*/
        return view;
    }


}
