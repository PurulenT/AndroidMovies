package com.example.androidmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServerResponce {

    @SerializedName("docs")
    private List<Movie> movieList;

    public ServerResponce(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    @Override
    public String toString() {
        return "ServerResponce{" +
                "movieList=" + movieList +
                '}';
    }
}
