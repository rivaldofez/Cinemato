package com.rivaldofez.core.datasource.remote.network

import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MoviesResponse
import com.rivaldofez.core.datasource.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): MoviesResponse

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): MoviesResponse

    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): MoviesResponse

    @GET("3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): MoviesResponse

    @GET("3/movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: String,
        @Query("key") key : String
    ): MovieDetailResponse?


    @GET("3/tv/popular")
    suspend fun getPopularTvShow(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): TvShowResponse

    @GET("3/tv/top_rated")
    suspend fun getTopRatedTvShow(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): TvShowResponse

    @GET("3/tv/on_the_air")
    suspend fun getOnAirTvShow(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): TvShowResponse

    @GET("3/tv/airing_today")
    suspend fun getAiringTodayTvShow(
        @Query("api_key") key: String,
        @Query("page") page: String
    ): TvShowResponse

    @GET("3/tv/{id}")
    suspend fun getDetailTvShow(
        @Path("id") id: String,
        @Query("key") key : String
    ): MovieDetailResponse?


}