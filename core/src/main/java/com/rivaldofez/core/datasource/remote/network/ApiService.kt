package com.rivaldofez.core.datasource.remote.network

import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MoviesResponse
import com.rivaldofez.core.datasource.remote.response.TvShowDetailResponse
import com.rivaldofez.core.datasource.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{type}")
    suspend fun getMovies(
        @Path("type") type: String,
        @Query("api_key") key: String,
        @Query("page") page: String,
    ): MoviesResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") key : String
    ): MovieDetailResponse?

    @GET("tv/{type}")
    suspend fun getTvShows(
        @Path("type") type: String,
        @Query("api_key") key: String,
        @Query("page") page: String,
    ): TvShowResponse

    @GET("tv/{id}")
    suspend fun getDetailTvShow(
        @Path("id") id: String,
        @Query("api_key") key : String
    ): TvShowDetailResponse?


}