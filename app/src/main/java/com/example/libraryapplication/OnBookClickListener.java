package com.example.libraryapplication;

public interface OnBookClickListener {
    void onItemClick(Book book);
    void onAddToWishListClick(Book book);
    void onAddToBookingsClick(Book book);
}

