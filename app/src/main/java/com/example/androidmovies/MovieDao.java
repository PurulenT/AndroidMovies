package com.example.androidmovies;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.google.android.material.circularreveal.CircularRevealHelper;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import retrofit2.http.DELETE;


@Dao
public interface MovieDao {
    @Query("SELECT * FROM favourite_movies")
    LiveData<List<Movie>> getAllFavouriteMovies();

    @Query("SELECT * FROM favourite_movies WHERE id=:movieId")
    LiveData<Movie> getFavouriteMovie(int movieId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertMovie(Movie movie);

    @Query("DELETE FROM favourite_movies WHERE id =:movieId")
    Completable removeMovie(int movieId);
}
