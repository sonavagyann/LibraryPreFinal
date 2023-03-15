package com.example.libraryapplication;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HomeFragment extends Fragment {

    private String[] genres;

    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<String> wishList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private BooksAdapter booksAdapter;

    private Context context;
    private View loading;
    private View container;
    private EditText searchView;
    private final CollectionReference db = FirebaseFirestore.getInstance().collection("Books");
    private ListenerRegistration dbListener;
    private ListenerRegistration userListener;

    public HomeFragment() {}

    @SuppressLint("MissingInflatedId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        if(!isConnected()){
            Toast.makeText(getContext(), "No Internet Access", Toast.LENGTH_SHORT).show();
        }

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView2 = view.findViewById(R.id.recyclerView2);
        recyclerView2.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);

        loading = view.findViewById(R.id.loading);
        container = view.findViewById(R.id.container);

        genres = getResources().getStringArray(R.array.genres);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        GenresAdapter genresAdapter = new GenresAdapter(getContext(), genres, position -> filterByGenre(position));

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(genresAdapter);

        booksAdapter = new BooksAdapter(true, new OnBookClickListener() {
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
        });

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

    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
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
                booksAdapter.setBooks(books);}loading.setVisibility(View.GONE);
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
                            ArrayList<String> ids = (ArrayList<String>) snapshots.get("ids");
                            if (ids != null) {
                                wishList.clear();
                                wishList.addAll(ids);
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
    }

    private void filterByKeyword(String book) {
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book item : books) {
            if (item.getTitle().toLowerCase().contains(book.toLowerCase()) ||
                    item.getAuthor().toLowerCase().contains(book.toLowerCase())) {
                filteredList.add(item);
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
            if (!wishList.contains(book.getId())) {
                wishList.add(book.getId());
                FirebaseFirestore.getInstance()
                        .collection("Users")
                        .document(user.getUid())
                        .update("ids", wishList)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Book added to wishlist", Toast.LENGTH_SHORT).show();
                            }
                        });
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

            db.document(book.getTitle()).update("isBooked", true).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    container.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Booked", Toast.LENGTH_SHORT).show();


                    Date date = new Date();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = dateFormat.format(date);
                } else {
                    task.getException().printStackTrace();
                    Toast.makeText(getContext(), "Error adding Book to booking", Toast.LENGTH_SHORT).show();
                }
                loading.setVisibility(View.GONE);
            });
        }
    }
}
