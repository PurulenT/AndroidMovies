package com.example.androidmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrailersList {
    @SerializedName("trailers")

    private List<Trailer> trailers;
    public List<Trailer> getTrailerList() {
        return trailers;
    }

    public TrailersList(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    @Override
    public String toString() {
        return "TrailersList{" +
                "trailers=" + trailers +
                '}';
    }
}
