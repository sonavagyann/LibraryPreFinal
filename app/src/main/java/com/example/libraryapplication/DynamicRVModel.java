package com.example.libraryapplication;

public class DynamicRVModel {

    String title, author, pages;//+descriptions
    int images;


    //+String descriptions
    public DynamicRVModel(String title, String author, String pages, int images) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.images = images;
        //this.descriptions=descriptions;
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

    //public String getDescriptions(){ return descriptions;}

}
