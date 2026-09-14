package com.example.androidmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {
    private final static String MOVIE_EXTRA = "movie";
    private ImageView imageViewPosterDetailed;
    private TextView textViewMovieTitleDetailed;
    private TextView textViewMovieYearDetailed;
    private TextView textViewMovieDescriptionDetailed;
    private MovieDetailViewModel viewModel;

    private RecyclerView recyclerViewTrailers;
    private TrailersAdapter trailersAdapter;

    private RecyclerView recyclerViewReviews;
    private ReviewAdapter reviewAdapter;

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
        trailersAdapter = new TrailersAdapter();
        recyclerViewTrailers.setAdapter(trailersAdapter);

        reviewAdapter = new ReviewAdapter();
        recyclerViewReviews.setAdapter(reviewAdapter);


        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);
        Movie movie = (Movie) getIntent().getSerializableExtra(MOVIE_EXTRA);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPosterDetailed);
        textViewMovieTitleDetailed.setText(movie.getName());
        textViewMovieYearDetailed.setText(String.valueOf(movie.getYear()));
        textViewMovieDescriptionDetailed.setText(movie.getDescription());

        viewModel.loadTrailers(movie.getId());
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailersAdapter.setTrailers(trailers);
                Log.d("MovieDetailActivity", trailers.toString());
            }
        });
        trailersAdapter.setOnImageClickListener(new TrailersAdapter.OnImageClickListener() {
            @Override
            public void onImageClick(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });

        viewModel.loadReviews(movie.getId());
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                reviewAdapter.setReviewList(reviews);
                Log.d("MovieDetailActivity", reviews.toString());
            }
        });

        MovieDao movieDao = MovieDatabase.getInstance(getApplication()).movieDao();
        movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private void initViews(){
        imageViewPosterDetailed = findViewById(R.id.imageViewPosterDetailed);
        textViewMovieTitleDetailed = findViewById(R.id.textViewMovieTitleDetailed);
        textViewMovieYearDetailed = findViewById(R.id.textViewMovieYearDetailed);
        textViewMovieDescriptionDetailed = findViewById(R.id.textViewMovieDescriptionDetailed);
        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
    }

    public static Intent newIntent(Context context, Movie movie){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE_EXTRA, movie);
        return intent;
    }
}