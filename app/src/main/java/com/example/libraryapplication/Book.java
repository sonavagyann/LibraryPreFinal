package com.example.libraryapplication;

public class Book {
    String genre, title, author, pages, descriptions, imageLink;
    public Book(){}

    public Book(String genre, String title, String author, String pages, String imageLink, String descriptions) {
        this.genre = genre;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.imageLink = imageLink;
        this.descriptions=descriptions;
    }

    public String getGenre(){return genre;}
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

    public String getDescriptions(){ return descriptions;}

}
