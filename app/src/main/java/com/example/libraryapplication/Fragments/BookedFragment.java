package com.example.libraryapplication.Fragments;

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

import com.example.libraryapplication.Book;
import com.example.libraryapplication.BookActivity;
import com.example.libraryapplication.BooksAdapter;
import com.example.libraryapplication.GenresAdapter;
import com.example.libraryapplication.OnBookClickListener;
import com.example.libraryapplication.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BookedFragment extends Fragment {
    private final ArrayList<Book> books = new ArrayList<>();
    private RecyclerView bookedRecyclerView;
    private BooksAdapter booksAdapter;

    private View loading;
    private View container;
    private final CollectionReference db = FirebaseFirestore.getInstance().collection("Books");
    private ListenerRegistration dbListener;

    public BookedFragment(){}

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_booked, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bookedRecyclerView = view.findViewById(R.id.booked_recyclerview);
        bookedRecyclerView.setHasFixedSize(true);

        loading = view.findViewById(R.id.booked_loading);
        container = view.findViewById(R.id.booked_container);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        bookedRecyclerView.setLayoutManager(layoutManager);

        booksAdapter = new BooksAdapter(false, new OnBookClickListener() {
            @Override
            public void onItemClick(Book book) {
                onBookClick(book);
            }

            @Override
            public void onAddToWishListClick(Book book) {}

            @Override
            public void onAddToBookingsClick(Book book) {}
        });
        bookedRecyclerView.setAdapter(booksAdapter);

        setUpFirestore();
    }

    @Override
    public void onDestroyView() {
        dbListener.remove();
        super.onDestroyView();
    }

    private void setUpFirestore() {
        loading.setVisibility(View.VISIBLE);
        dbListener = db.whereEqualTo("isBooked", true).addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                error.printStackTrace();
                return;
            }
            System.out.println(snapshots);
            if (snapshots != null ) {
                container.setVisibility(View.VISIBLE);
                books.clear();
                books.addAll(snapshots.toObjects(Book.class));
                booksAdapter.setBooks(books);
            }
            loading.setVisibility(View.GONE);
        });
    }

    private void onAddToWishList(Book book) {}

    private void onAddToBookings(Book book) {}

    private void onBookClick(Book book) {
        Intent intent = new Intent(getContext(), BookActivity.class);

        intent.putExtra("Name", book.getTitle());
        intent.putExtra("Author", book.getAuthor());
        intent.putExtra("Pages", book.getPages());
        intent.putExtra("Description", book.getDescription());
        intent.putExtra("Image", book.getImageLink());

        startActivity(intent);
    }
}