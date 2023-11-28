package com.revaldoputra.movieapp.retrofit;

import com.revaldoputra.movieapp.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie/popular")
    Call<MovieModel> getPopularMovie(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") String page
    );
}
