package com.example.libraryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class HomeFeedActivity extends AppCompatActivity implements RVInterface, UpdateGenre{

    ArrayList<StaticRVModel> staticRVModels = new ArrayList<>();
    ArrayList<DynamicRVModel> dynamicRVModels = new ArrayList<>();
    ArrayList<DynamicRVModel> searchBook;

    DynamicRVAdapter dynamicRVAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;

    SearchView searchView;
    int[] images = {R.drawable.and_still_i_rise_cover, R.drawable.call_us_cover,
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


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        setUpStaticRVModels();
        setUpDynamicRVModels();




        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        /*
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBook = new ArrayList<>();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //filterList(newText);
                return true;
            }
        });*/



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView2 = findViewById(R.id.recyclerView2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        StaticRVAdapter staticRVAdapter = new StaticRVAdapter((UpdateGenre) this,getParent(),staticRVModels);
        recyclerView.setAdapter(staticRVAdapter);
        recyclerView.setLayoutManager(layoutManager);

        DynamicRVAdapter dynamicRVAdapter= new DynamicRVAdapter(this, dynamicRVModels, this);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        searchView = findViewById(R.id.searchView);


        /*
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchBook = new ArrayList<>();

                if(query.length()>0){
                    for (int i = 0; i < dynamicRVModels.size(); i++) {
                        if(dynamicRVModels.get(i).getTitle().toUpperCase().contains(query.toUpperCase())){

                            DynamicRVModel dynamicModel = new DynamicRVModel();
                            dynamicModel.setTitle(dynamicRVModels.get(i).getTitle());
                            dynamicModel.setAuthor(dynamicRVModels.get(i).getAuthor());
                            dynamicModel.setPages(dynamicRVModels.get(i).getPages());
                            dynamicModel.setDescriptions(dynamicRVModels.get(i).getDescriptions());
                            dynamicModel.setImages(dynamicRVModels.get(i).getImages());
                        }
                    }

                    DynamicRVAdapter dynamicRVAdapter= new DynamicRVAdapter(HomeFeedActivity.this, searchBook, HomeFeedActivity.this);
                    recyclerView2.setAdapter(dynamicRVAdapter);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(HomeFeedActivity.this));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                dynamicRVAdapter.getFilter().filter(newText);
                return false;
            }
        });*/
    }

    private void setUpStaticRVModels(){
        String[] genres = getResources().getStringArray(R.array.genres);

        for(int i = 0; i<genres.length; i++){
            staticRVModels.add(new StaticRVModel(genres[i]));
        }
    }


    private void setUpDynamicRVModels(){
        String[] titles = getResources().getStringArray(R.array.books);
        String[] authors = getResources().getStringArray(R.array.authors);
        String[] pages = getResources().getStringArray(R.array.pages);
        String[] descriptions = getResources().getStringArray(R.array.descriptions);

        for(int i = 0; i<titles.length; i++){
            dynamicRVModels.add(new DynamicRVModel(titles[i],
                    authors[i], pages[i], images[i], descriptions[i]));
        }
    }






    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HomeFeedActivity.this, BooksActivity.class);

        intent.putExtra("Name", dynamicRVModels.get(position).getTitle());
        intent.putExtra("Author", dynamicRVModels.get(position).getAuthor());
        intent.putExtra("Pages", dynamicRVModels.get(position).getPages());
        intent.putExtra("Description", dynamicRVModels.get(position).getDescriptions());
        intent.putExtra("Image", dynamicRVModels.get(position).getImages());

        startActivity(intent);
    }


    @Override
    public void showBooks(int position, ArrayList<DynamicRVModel> list) {
        dynamicRVAdapter = new DynamicRVAdapter(this, list, this);
        dynamicRVAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRVAdapter);
    }
}
