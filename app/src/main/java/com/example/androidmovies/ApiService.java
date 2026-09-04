package com.example.androidmovies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v1.4/movie?token=FNE5FN8-B0KM23T-KBAA52K-G7G30Q8&rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=10")
    Single<ServerResponce> getMovies(@Query("page") int page);
}
