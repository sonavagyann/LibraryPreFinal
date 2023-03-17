package com.example.libraryapplication;

import com.example.libraryapplication.Adapters.Book;

public interface OnBookClickListener {
    void onItemClick(Book book);
    void onAddToWishListClick(Book book);
    void onAddToBookingsClick(Book book);
}

