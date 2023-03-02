package com.example.libraryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import coil.Coil;
import coil.request.ImageRequest;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        String name = getIntent().getStringExtra("Name");
        String author = getIntent().getStringExtra("Author");
        String pages = getIntent().getStringExtra("Pages");
        String description = getIntent().getStringExtra("Description");
        String imageLink = getIntent().getStringExtra("Image");

        TextView book_name = findViewById(R.id.book_title);
        TextView book_author = findViewById(R.id.book_author);
        TextView book_pages = findViewById(R.id.book_pages);
        TextView book_description = findViewById(R.id.book_description);
        ImageView book_cover = findViewById(R.id.book_cover);

        book_name.setText(name);
        book_author.setText(author);
        book_pages.setText(pages);
        book_description.setText(description);
        ImageRequest request = new ImageRequest.Builder(this).data(imageLink).target(book_cover).build();
        Coil.imageLoader(this).enqueue(request);
    }
}