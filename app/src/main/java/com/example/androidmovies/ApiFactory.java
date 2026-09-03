package com.example.androidmovies;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private final static String BASE_URL = "https://api.poiskkino.dev/";
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();
    public static final ApiService apiService = retrofit.create(ApiService.class); //the same to singleton
}
