package com.rivaldofez.core.datasource.remote.network

import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface  ApiService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): MoviesResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: String,
        @Query("key") key : String
    ): MovieDetailResponse?
}