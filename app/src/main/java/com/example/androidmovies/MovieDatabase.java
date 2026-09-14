package com.example.androidmovies;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public static MovieDatabase instance;
    public static MovieDatabase getInstance(Application application){
        if(instance == null){
            instance = Room.databaseBuilder(
                    application,
                    MovieDatabase.class,
                    "Movie_DB"
            ).build();
        }
        return instance;
    }

    abstract MovieDao movieDao();
}
