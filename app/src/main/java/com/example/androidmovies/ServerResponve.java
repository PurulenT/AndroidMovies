package com.example.androidmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerResponve {

    @SerializedName("docs")
    private List<Movie> movieList;

    public ServerResponve(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
