package com.example.libraryapplication.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapplication.Adapters.Book;
import com.example.libraryapplication.BookActivity;
import com.example.libraryapplication.Adapters.BooksAdapter;
import com.example.libraryapplication.OnBookClickListener;
import com.example.libraryapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WishlistFragment extends Fragment {

    Context context;
    private final ArrayList<Book> books = new ArrayList<>();
    private RecyclerView wish_recyclerView;
    private BooksAdapter booksAdapter;
    private View loading;
    private View container;

    public WishlistFragment(){}

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wishlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!isConnected()){
            Toast.makeText(getContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }

        wish_recyclerView = view.findViewById(R.id.wishlist_recyclerview);
        wish_recyclerView.setHasFixedSize(true);

        loading = view.findViewById(R.id.wishlist_loading);
        container = view.findViewById(R.id.wishlist_container);

        int removeWishlistImg= R.drawable.remove_button;
        int removeBookedImg= R.drawable.unbook_icon;
        booksAdapter = new BooksAdapter(true, false, new OnBookClickListener() {
            @Override
            public void onItemClick(Book book) {
                onBookClick(book);
            }

            @Override
            public void onAddToWishListClick(Book book) {
                onRemoveFromWishList(book);
            }

            @Override
            public void onAddToBookingsClick(Book book){}
        }, true, removeWishlistImg, false, removeBookedImg);

        wish_recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        wish_recyclerView.setAdapter(booksAdapter);

        setUpFirestore();
    }

    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }


    private void setUpFirestore() {
        loading.setVisibility(View.VISIBLE);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            FirebaseFirestore.getInstance()
                    .collection("Users")
                    .document(user.getUid())
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            List<Map<String, Object>> result = (List<Map<String, Object>>) task.getResult().getData().get("books");
                            if(result!= null) {
                                books.clear();

                                for (Map<String, Object> map : result) {
                                    map.get("id");
                                    map.get("genre");
                                    map.get("title");
                                    map.get("author");
                                    map.get("pages");
                                    map.get("imageLink");
                                    map.get("descriptions");
                                    map.get("isBooked");
                                    Book book = new Book((String) map.get("id"), (String) map.get("genre"), (String) map.get("title"), (String) map.get("author"),
                                            (String) map.get("pages"), (String) map.get("imageLink"), (String) map.get("description"), (Boolean) map.get("isBooked"));
                                    books.add(book);


                                }
                                container.setVisibility(View.VISIBLE);
                                booksAdapter.setBooks(books);
                            }
                        } else {
                            Toast.makeText(getContext(), "Error Fetching wishlist", Toast.LENGTH_SHORT).show();
                        }
                        loading.setVisibility(View.GONE);
                    });
        }
    }

    private void onBookClick(Book book) {
        Intent intent = new Intent(getContext(), BookActivity.class);

        intent.putExtra("Name", book.getTitle());
        intent.putExtra("Author", book.getAuthor());
        intent.putExtra("Pages", book.getPages());
        intent.putExtra("Description", book.getDescription());
        intent.putExtra("Image", book.getImageLink());

        startActivity(intent);
    }

    private void onRemoveFromWishList(Book book) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (books.contains(book)) {
                books.remove(book);
                booksAdapter.setBooks(books);
                FirebaseFirestore.getInstance()
                        .collection("Users")
                        .document(user.getUid())
                        .update("books", books)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Book removed from wishlist", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }
    }

}