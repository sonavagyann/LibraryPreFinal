package com.example.libraryapplication.Adapters;

import java.util.Objects;
public class Book {
    private String id, genre, title, author, pages, description, imageLink;
    private Boolean isBooked;

    public Book() {}

    public Book(String id, String genre, String title, String author, String pages, String imageLink, String descriptions, Boolean isBooked) {
        this.id = id;
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

    public String getId() {return id;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;
        return Objects.equals(id, other.id) && Objects.equals(genre, other.genre) &&
                Objects.equals(title, other.title) && Objects.equals(author, other.author) &&
                Objects.equals(pages, other.pages) && Objects.equals(imageLink, other.imageLink) &&
                Objects.equals(description, other.description) && isBooked == other.isBooked;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, genre, title, author, pages, imageLink, description, isBooked);
    }
}
