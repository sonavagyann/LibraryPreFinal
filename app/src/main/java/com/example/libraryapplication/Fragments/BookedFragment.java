package com.example.libraryapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapplication.Book;
import com.example.libraryapplication.BooksAdapter;
import com.example.libraryapplication.R;

import java.util.ArrayList;

public class BookedFragment extends Fragment {

    ArrayList<Book> dynamicRVModels = new ArrayList<>();
    BooksAdapter dynamicRVAdapter;
    private RecyclerView favRecyclerView;


    //private HomeFeedActivity homeFeedActivity;
    public BookedFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booked,container,false);


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