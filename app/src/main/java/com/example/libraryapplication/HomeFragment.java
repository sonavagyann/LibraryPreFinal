package com.example.libraryapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    ArrayList<StaticRVModel> staticRVModels = new ArrayList<>();
    ArrayList<DynamicRVModel> dynamicRVModels = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    int images[] = {R.drawable.hunger_games_cover, R.drawable.catching_fire_cover,
            R.drawable.book_thief_cover, R.drawable.halfblood_prince_cover,
            R.drawable.call_us_cover, R.drawable.forty_days_cover,
            R.drawable.murder_of_roger_cover};

    public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpStaticRVModels();
        setUpDynamicRVModels();
        if(getArguments() != null){

        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 =  view.findViewById(R.id.recyclerView2);

        StaticRVAdapter staticRVAdapter = new StaticRVAdapter(getContext(), staticRVModels);
        recyclerView.setAdapter(staticRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        DynamicRVAdapter dynamicRVAdapter= new DynamicRVAdapter(getContext(), dynamicRVModels);//+(RVInterface) getContext()
        recyclerView2.setAdapter(dynamicRVAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    private void setUpStaticRVModels(){
        String[] genres = getResources().getStringArray(R.array.genres);

        for(int i = 0; i<genres.length; i++){
            staticRVModels.add(new StaticRVModel(genres[i]));
        }
    }
    private void setUpDynamicRVModels() {
        String[] titles = getResources().getStringArray(R.array.books);
        String[] authors = getResources().getStringArray(R.array.authors);
        String[] pages = getResources().getStringArray(R.array.pages);
        //String[] descriptions = getResources().getStringArray(R.array.descriptions);

        for (int i = 0; i < titles.length; i++) {
            dynamicRVModels.add(new DynamicRVModel(titles[i],
                    authors[i], pages[i], images[i]));//+descriptions[i])
        }
    }

}
