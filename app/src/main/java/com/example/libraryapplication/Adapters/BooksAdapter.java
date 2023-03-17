package com.example.libraryapplication.Adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapplication.OnBookClickListener;
import com.example.libraryapplication.R;

import coil.Coil;
import coil.request.ImageRequest;
import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {
    private final OnBookClickListener listener;

    private final List<Book> books = new ArrayList<>();

    private boolean showBooking, showWish;

    public BooksAdapter(boolean showWish, boolean showBooking, OnBookClickListener listener) {
        this.showWish = showWish;
        this.listener = listener;
        this.showBooking = showBooking;
    }

    @NonNull
    @Override
    public BooksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_display_layout, parent, false);
        return new BooksAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        ImageRequest request = new ImageRequest.Builder(holder.myImage.getContext()).data(book.getImageLink())
                .target(holder.myImage).build();
        Coil.imageLoader(holder.myImage.getContext()).enqueue(request);
        int bookingVisibility;
        int wishVisibility;
        if (showBooking) {
            bookingVisibility = View.VISIBLE;
        } else {
            bookingVisibility = View.GONE;
        }

        if (showWish) {
            wishVisibility = View.VISIBLE;
        } else {
            wishVisibility = View.GONE;
        }
        holder.addFavImg.setVisibility(bookingVisibility);
        holder.addFavImg.setImageResource(R.drawable.baseline_access_time_24);
        holder.addFavImg.setOnClickListener(view -> listener.onAddToBookingsClick(book));
        holder.addReadImg.setVisibility(wishVisibility);
        holder.addReadImg.setImageResource(R.drawable.baseline_add_24);
        holder.addReadImg.setOnClickListener(view -> listener.onAddToWishListClick(book));
        holder.itemView.setOnClickListener(view -> listener.onItemClick(book));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> books) {
        this.books.clear();
        this.books.addAll(books);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, author, pages;
        ImageView myImage, addFavImg, addReadImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.book_title);
            author = itemView.findViewById(R.id.book_author);
            pages = itemView.findViewById(R.id.book_pages);
            myImage = itemView.findViewById(R.id.book_cover);
            addFavImg = itemView.findViewById(R.id.addToFav);
            addReadImg = itemView.findViewById(R.id.addToRead);
        }
    }
}
