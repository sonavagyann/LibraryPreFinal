package com.example.libraryapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import coil.Coil;
import coil.request.ImageRequest;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {
    private Context context;
    private OnBookClickListener listener;
    private List<Book> books;

    public BooksAdapter(Context context, OnBookClickListener listener){
        this.context=context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public BooksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item_layout, parent, false);
        return new BooksAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Book book = books.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.pages.setText(book.getPages());
        ImageRequest request = new ImageRequest.Builder(context).data(book.getImageLink()).target(holder.myImage).build();
        Coil.imageLoader(context).enqueue(request);
        holder.addFavImg.setImageResource(R.drawable.add_favorite);
        holder.addFavImg.setOnClickListener(view -> Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show());
        holder.addReadImg.setImageResource(R.drawable.baseline_add_24);
        holder.addReadImg.setOnClickListener(view -> Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show());
        holder.itemView.setOnClickListener(view -> listener.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void filterList(ArrayList<Book> filteredList){
        books = filteredList;
        notifyDataSetChanged();
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView genres, title, author, pages;
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
