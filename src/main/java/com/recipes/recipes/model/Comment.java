package com.recipes.recipes.model;

public class Comment {
    private String author;
    private String text;
    private int rating;

    // Constructor
    public Comment(String author, String text, int rating) {
        this.author = author;
        this.text = text;
        this.rating = rating;
    }

    // Getters y Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}