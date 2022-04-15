package com.gnova.data.api

import com.gnova.data.api.response.MovieResponse
 import io.reactivex.Single
import retrofit2.http.GET
 import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String,
        @Query("include_adult") includeAdult: String,
        @Query("include_video") includeVideo: String,
        @Query("page") page: Int
    ): Single<MovieResponse>


}