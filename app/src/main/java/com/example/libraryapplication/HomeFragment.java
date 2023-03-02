package com.example.libraryapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements OnTabChangeListener {

    String[] genres;
    ArrayList<Book> books = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    BooksAdapter booksAdapter;

    View loading;
    View container;
    EditText searchView;

    public HomeFragment() {
    }

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);

        loading = view.findViewById(R.id.loading);
        container = view.findViewById(R.id.container);

        genres = getResources().getStringArray(R.array.genres);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        GenresAdapter genresAdapter = new GenresAdapter(getContext(), genres, new OnTabChangeListener() {
            @Override
            public void onTabChange(int position) {
                filterByGenre(position);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(genresAdapter);

        booksAdapter = new BooksAdapter(getContext(), this::onBookClick);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(booksAdapter);

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
                filterByKeyword(editable.toString());
            }

        });

        setUpFirestore();

    }

    private void setUpFirestore() {
        loading.setVisibility(View.VISIBLE);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Books").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                container.setVisibility(View.VISIBLE);
                books.clear();
                books.addAll(task.getResult().toObjects(Book.class));
                booksAdapter.setBooks(books);
            } else {
                Toast.makeText(getContext(), "Error Fetching Books", Toast.LENGTH_SHORT).show();
            }
            loading.setVisibility(View.GONE);
        });
    }

    private void filterByGenre(int position) {
        ArrayList<Book> filteredList = new ArrayList<>();

        if(position==0){
            filteredList = books;
        }
        else{
            String genre = genres[position];
            for (Book item : books) {
                if (item.getGenre().toLowerCase().equals(genre)){
                    filteredList.add(item);
                }
            }
        }
        booksAdapter.filterList(filteredList);
    }

    private void filterByKeyword(String book) {
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book item : books) {
            if (item.getTitle().toLowerCase().contains(book.toLowerCase()) || item.getAuthor().toLowerCase().contains(book.toLowerCase())) {
                filteredList.add(item);
            }
        }

        booksAdapter.filterList(filteredList);
    }

    private void onBookClick(int position) {
        Book book = books.get(position);
        Intent intent = new Intent(getContext(), BookActivity.class);

        intent.putExtra("Name", book.getTitle());
        intent.putExtra("Author", book.getAuthor());
        intent.putExtra("Pages", book.getPages());
        intent.putExtra("Description", book.getDescriptions());
        intent.putExtra("Image", book.getImageLink());

        startActivity(intent);

    }

    @Override
    public void onTabChange(int position) {

    }
}
