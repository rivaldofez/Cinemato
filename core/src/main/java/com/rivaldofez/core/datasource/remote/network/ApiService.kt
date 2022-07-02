package com.rivaldofez.core.datasource.remote.network

import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): MoviesResponse

    @GET("3/movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: String,
        @Query("key") key : String
    ): MovieDetailResponse?
}