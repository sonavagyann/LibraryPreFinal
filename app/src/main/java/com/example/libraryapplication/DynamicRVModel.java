package com.example.libraryapplication;

public class DynamicRVModel {

    String title, author, pages, descriptions;
    int images;

    public DynamicRVModel(String title, String author, String pages, int images, String descriptions) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.images = images;
        this.descriptions=descriptions;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPages() {
        return pages;
    }

    public int getImages() {
        return images;
    }

    public String getDescriptions(){ return descriptions;}



    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setImages(int images) {
        this.images = images;
    }

}
