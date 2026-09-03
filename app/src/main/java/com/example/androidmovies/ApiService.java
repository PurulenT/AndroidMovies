package com.example.androidmovies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("v1.4/movie?token=FNE5FN8-B0KM23T-KBAA52K-G7G30Q8&rating.kp=7-10&sortField=votes.kp&sortType=-1&limit=40")
    Single<ServerResponce> getMovies();
}
