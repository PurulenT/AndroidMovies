package com.example.androidmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
    private final static String MOVIE_EXTRA = "movie";
    private ImageView imageViewPosterDetailed;
    private TextView textViewMovieTitleDetailed;
    private TextView textViewMovieYearDetailed;
    private TextView textViewMovieDescriptionDetailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_EXTRA);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPosterDetailed);
        textViewMovieTitleDetailed.setText(movie.getName());
        textViewMovieYearDetailed.setText(String.valueOf(movie.getYear()));
        textViewMovieDescriptionDetailed.setText(movie.getDescription());

        ApiFactory.apiService.loadTrailers(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrailerResponse>() {
                    @Override
                    public void accept(TrailerResponse trailerResponse) throws Throwable {
                        Log.d("MovieDetailActivity", trailerResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("MovieDetailActivity", throwable.toString());
                    }
                });

    }

    private void initViews(){
        imageViewPosterDetailed = findViewById(R.id.imageViewPosterDetailed);
        textViewMovieTitleDetailed = findViewById(R.id.textViewMovieTitleDetailed);
        textViewMovieYearDetailed = findViewById(R.id.textViewMovieYearDetailed);
        textViewMovieDescriptionDetailed = findViewById(R.id.textViewMovieDescriptionDetailed);
    }

    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_EXTRA, movie);
        return intent;
    }
}