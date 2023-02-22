package com.example.libraryapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements RVInterface, UpdateGenre{

    ArrayList<StaticRVModel> staticRVModels = new ArrayList<>();
    ArrayList<DynamicRVModel> dynamicRVModels = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    DynamicRVAdapter dynamicRVAdapter;

    EditText searchView;

    int images[] = {R.drawable.and_still_i_rise_cover, R.drawable.call_us_cover,
            R.drawable.catching_fire_cover, R.drawable.forty_days_cover,
            R.drawable.hamlet_cover, R.drawable.halfblood_prince_cover,
            R.drawable.jane_eyre_cover, R.drawable.me_before_you,
            R.drawable.mere_christianity, R.drawable.murder_of_roger_cover,
            R.drawable.romeo_and_juliet, R.drawable.samvel_cover,
            R.drawable.alchemist_cover, R.drawable.the_big_sleep_cover,
            R.drawable.urbatagirk_cover, R.drawable.matyan_cover,
            R.drawable.book_thief_cover, R.drawable.fault_in_our_stars_cover,
            R.drawable.gatsby_cover, R.drawable.the_hound_cover,
            R.drawable.hunger_games_cover, R.drawable.the_sun_and_her_flowers_cover,
            R.drawable.vardanank_cover, R.drawable.nineteen_eighty_four_cover};

    public HomeFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getArguments() != null){

        }

        setUpStaticRVModels();
        setUpDynamicRVModels();

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 =  view.findViewById(R.id.recyclerView2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        StaticRVAdapter staticRVAdapter = new StaticRVAdapter(this, getActivity(), staticRVModels);
        recyclerView.setAdapter(staticRVAdapter);
        recyclerView.setLayoutManager(layoutManager);


        DynamicRVAdapter dynamicRVAdapter= new DynamicRVAdapter(getContext(), dynamicRVModels, this);
        recyclerView2.setAdapter(dynamicRVAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));


        searchView = (EditText) view.findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }

        });

        return view;
    }


    private void filter(String book){
        ArrayList<DynamicRVModel> filteredList = new ArrayList<>();

        for(DynamicRVModel item : dynamicRVModels){
            if(item.getTitle().toLowerCase().contains(book.toLowerCase()) || item.getAuthor().toLowerCase().contains(book.toLowerCase())){
                filteredList.add(item);
            }
        }

        dynamicRVAdapter.filterList(filteredList);
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
        String[] descriptions = getResources().getStringArray(R.array.descriptions);

        for (int i = 0; i < titles.length; i++) {
            dynamicRVModels.add(new DynamicRVModel(titles[i],
                    authors[i], pages[i], images[i], descriptions[i]));
        }
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), BooksActivity.class);

        intent.putExtra("Name", dynamicRVModels.get(position).getTitle());
        intent.putExtra("Author", dynamicRVModels.get(position).getAuthor());
        intent.putExtra("Pages", dynamicRVModels.get(position).getPages());
        intent.putExtra("Description", dynamicRVModels.get(position).getDescriptions());
        intent.putExtra("Image", dynamicRVModels.get(position).getImages());

        startActivity(intent);
    }


    @Override
    public void showBooks(int position, ArrayList<DynamicRVModel> list) {

        dynamicRVAdapter = new DynamicRVAdapter(getContext(), list, this);
        dynamicRVAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRVAdapter);
    }

}
