package com.example.pesantiketbioskop;

public class Movie {

    String title, genre, rating, description;
    int image;

    public Movie(String title, String genre, String rating,
                 String description, int image) {

        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public int getImage() {
        return image;
    }
}
