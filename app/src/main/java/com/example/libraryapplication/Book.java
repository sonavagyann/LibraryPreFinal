package com.example.libraryapplication;

public class Book {
    private String genre, title, author, pages, description, imageLink;
    private Boolean isBooked;

    public Book() {}

    public Book(String genre, String title, String author, String pages, String imageLink, String descriptions, Boolean isBooked) {
        this.genre = genre;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.imageLink = imageLink;
        this.description = descriptions;
        this.isBooked = isBooked;
    }

    public String getGenre() {return genre;}

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPages() {
        return pages;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {return description;}

    public Boolean getBooked() {
        return isBooked;
    }

    public void setBooked(Boolean booked) {
        isBooked = booked;
    }
}
