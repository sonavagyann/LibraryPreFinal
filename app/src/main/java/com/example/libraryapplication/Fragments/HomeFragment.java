package com.example.libraryapplication.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
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

import com.example.libraryapplication.Adapters.Book;
import com.example.libraryapplication.Adapters.BooksAdapter;
import com.example.libraryapplication.Adapters.GenresAdapter;
import com.example.libraryapplication.BookActivity;
import com.example.libraryapplication.OnBookClickListener;
import com.example.libraryapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private String[] genres;

    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<Book> wishList = new ArrayList<>();
    private final ArrayList<Book> booked = new ArrayList<>();
    private BooksAdapter booksAdapter;

    private Context context;
    private View loading;
    private View container;
    private final CollectionReference db = FirebaseFirestore.getInstance().collection("Books");
    private ListenerRegistration dbListener;
    private ListenerRegistration userListener;

    public HomeFragment() {
    }

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        if (!isConnected()) {
            Toast.makeText(getContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);

        loading = view.findViewById(R.id.loading);
        container = view.findViewById(R.id.container);

        genres = getResources().getStringArray(R.array.genres);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        GenresAdapter genresAdapter = new GenresAdapter(getContext(), genres, this::filterByGenre);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(genresAdapter);

        recyclerView.scrollToPosition(0);
        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(0);
        if (viewHolder != null && viewHolder instanceof GenresAdapter.MyViewHolder) {
            viewHolder.itemView.performClick();
        }

        int removeWishlistImg = R.drawable.remove_button;
        int removeBookedImg = R.drawable.unbook_icon;
        booksAdapter = new BooksAdapter(true, true, new OnBookClickListener() {
            @Override
            public void onItemClick(Book book) {
                onBookClick(book);
            }

            @Override
            public void onAddToWishListClick(Book book) {
                onAddToWishList(book);
            }

            @Override
            public void onAddToBookingsClick(Book book) {
                onAddToBookings(book);
            }
        }, false, removeWishlistImg, false, removeBookedImg);

        recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView2.setAdapter(booksAdapter);

        EditText searchView = (EditText) view.findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchKeyword = editable.toString();
                String selectedGenre = genresAdapter.getSelectedGenre();
                filterByKeyword(searchKeyword, selectedGenre);
            }
        });

        setUpFirestore();
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    @Override
    public void onDestroyView() {
        dbListener.remove();
        userListener.remove();
        super.onDestroyView();
    }

    private void setUpFirestore() {
        loading.setVisibility(View.VISIBLE);
        dbListener = db.whereEqualTo("isBooked", false).addSnapshotListener((snapshots, error) -> {
            if (error != null) {
                Toast.makeText(getContext(), "Error Fetching Books", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                return;
            }
            if (snapshots != null) {
                container.setVisibility(View.VISIBLE);
                books.clear();
                books.addAll(snapshots.toObjects(Book.class));
                booksAdapter.setBooks(books);
            }
            loading.setVisibility(View.GONE);
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userListener = FirebaseFirestore.getInstance()
                    .collection("Users")
                    .document(user.getUid())
                    .addSnapshotListener((snapshots, error) -> {
                        if (error != null) {
                            error.printStackTrace();
                            return;
                        }

                        if (snapshots != null) {
                            List<Map<String, Object>> result = (List<Map<String, Object>>) snapshots.get("books");
                            if (result != null) {
                                wishList.clear();
                                for (Map<String, Object> map : result) {
                                    map.get("id");
                                    map.get("genre");
                                    map.get("title");
                                    map.get("author");
                                    map.get("pages");
                                    map.get("imageLink");
                                    map.get("description");
                                    map.get("isBooked");
                                    Book book = new Book((String) map.get("id"), (String) map.get("genre"), (String) map.get("title"), (String) map.get("author"),
                                            (String) map.get("pages"), (String) map.get("imageLink"), (String) map.get("description"), (Boolean) map.get("isBooked"));
                                    wishList.add(book);
                                }
                            }
                            List<Map<String, Object>> result1 = (List<Map<String, Object>>) snapshots.get("reserved");
                            if (result1 != null) {
                                booked.clear();
                                for (Map<String, Object> map : result1) {
                                    map.get("id");
                                    map.get("genre");
                                    map.get("title");
                                    map.get("author");
                                    map.get("pages");
                                    map.get("imageLink");
                                    map.get("description");
                                    map.get("isBooked");
                                    Book book = new Book((String) map.get("id"), (String) map.get("genre"), (String) map.get("title"), (String) map.get("author"),
                                            (String) map.get("pages"), (String) map.get("imageLink"), (String) map.get("description"), (Boolean) map.get("isBooked"));
                                    booked.add(book);
                                }
                                List<Book> filteredBooks = new ArrayList<>(books);
                                for (Book book : booked) {
                                    filteredBooks.remove(book);
                                }
                                booksAdapter.setBooks(filteredBooks);
                            }
                        }
                    });
        }
    }

    private void filterByGenre(int position) {
        ArrayList<Book> filteredList = new ArrayList<>();

        if (position == 0) {
            filteredList = books;
        } else {
            String genre = genres[position];
            for (Book item : books) {
                System.out.println(item.getGenre() + " - " + genre);
                if (item.getGenre().equalsIgnoreCase(genre)) {
                    filteredList.add(item);
                }
            }
        }
        booksAdapter.setBooks(filteredList);
        String selectedGenre = "";
        if (position == 0) {
            selectedGenre = "";
        } else {
            selectedGenre = genres[position];
        }
    }

    private void filterByKeyword(String keyword, String category) {
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book book : books) {
            if ((book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) &&
                    (category.equals("All") || book.getGenre().equals(category))) {
                filteredList.add(book);
            }
        }
        booksAdapter.setBooks(filteredList);
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

    private void onAddToWishList(Book book) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (!wishList.contains(book)) {
                wishList.add(book);
                FirebaseFirestore.getInstance()
                        .collection("Users")
                        .document(user.getUid())
                        .update("books", wishList)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Added to wishlist", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                Toast.makeText(getContext(), "Already added to wishlist", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onAddToBookings(Book book) {
        if(!isConnected()){
            Toast.makeText(getContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }
        else {
            container.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);

            long timestamp = System.currentTimeMillis();

            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
            String bookedAt = formatter.format(new Date(timestamp));

            db.document(book.getTitle()).update("isBooked", true, "bookedBy", FirebaseAuth.getInstance().getCurrentUser().getUid(), "bookedAt", bookedAt)
                    .addOnCompleteListener(task -> {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            if (!booked.contains(book)) {
                                booked.add(book);
                                FirebaseFirestore.getInstance()
                                        .collection("Users")
                                        .document(user.getUid())
                                        .update("reserved", booked)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(getContext(), "Booked", Toast.LENGTH_SHORT).show();
                                                books.remove(book);
                                                booksAdapter.setBooks(books);
                                            }
                                        });
                            } else {
                                Toast.makeText(getContext(), "Already booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                        loading.setVisibility(View.GONE);
                    });
        }
    }
}