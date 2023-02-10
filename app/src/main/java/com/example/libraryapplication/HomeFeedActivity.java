package com.example.libraryapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;

//implements RVInterface nerqevy
public class HomeFeedActivity extends AppCompatActivity{

    ArrayList<StaticRVModel> staticRVModels = new ArrayList<>();
    ArrayList<DynamicRVModel> dynamicRVModels = new ArrayList<>();

    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    int images[] = {R.drawable.hunger_games_cover, R.drawable.catching_fire_cover,
            R.drawable.book_thief_cover, R.drawable.halfblood_prince_cover,
            R.drawable.call_us_cover, R.drawable.forty_days_cover,
            R.drawable.murder_of_roger_cover};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        setUpStaticRVModels();
        setUpDynamicRVModels();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView2 = findViewById(R.id.recyclerView2);

        StaticRVAdapter staticRVAdapter = new StaticRVAdapter(this, staticRVModels);
        recyclerView.setAdapter(staticRVAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DynamicRVAdapter dynamicRVAdapter= new DynamicRVAdapter(this, dynamicRVModels);//+this
        recyclerView2.setAdapter(dynamicRVAdapter);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
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
                    authors[i], pages[i], images[i]));//+descriptions[i]
        }
    }

    /*
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HomeFeedActivity.this, BooksActivity.class);

        intent.putExtra("Name", dynamicRVModels.get(position).getTitle());
        intent.putExtra("Author", dynamicRVModels.get(position).getAuthor());
        intent.putExtra("Pages", dynamicRVModels.get(position).getPages());
        intent.putExtra("Plot", dynamicRVModels.get(position).getDescriptions());
        intent.putExtra(" ", dynamicRVModels.get(position).getImages());

        startActivity(intent);
    }*/

}
