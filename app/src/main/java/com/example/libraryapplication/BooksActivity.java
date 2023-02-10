package com.example.libraryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        String name = getIntent().getStringExtra("Name");
        String author = getIntent().getStringExtra("Author");
        String pages = getIntent().getStringExtra("Pages");
        String description = getIntent().getStringExtra("Plot");
        int image = getIntent().getIntExtra(" ", 0);

        TextView book_name = findViewById(R.id.book_title);
        TextView book_author = findViewById(R.id.book_author);
        TextView book_pages = findViewById(R.id.book_pages);
        TextView book_description = findViewById(R.id.book_description);
        ImageView book_cover = findViewById(R.id.book_cover);

        book_name.setText(name);
        book_author.setText(author);
        book_pages.setText(pages);
        book_description.setText(description);
        book_cover.setImageResource(image);
    }
}