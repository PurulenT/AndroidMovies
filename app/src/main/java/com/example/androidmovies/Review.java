package com.example.androidmovies;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("title")
    private String title;
    @SerializedName("author")
    private String author;
    @SerializedName("review")
    private String review;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("type")
    private String type;

    public Review(String title, String author, String review, String createdAt, String type) {
        this.title = title;
        this.author = author;
        this.review = review;
        this.createdAt = createdAt;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Review{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", review='" + review + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", isPositive=" + type +
                '}';
    }
}
