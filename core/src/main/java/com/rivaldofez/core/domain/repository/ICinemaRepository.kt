package com.rivaldofez.core.domain.repository

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.datasource.remote.response.TvShowListItem
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail
import kotlinx.coroutines.flow.Flow

interface ICinemaRepository {

    fun getPopularMovies(page: String): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(page: String): Flow<Resource<List<Movie>>>

    fun getUpComingMovies(page: String): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovies(page: String): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>>

    fun getPopularTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getTopRatedTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getOnTheAirTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getAiringTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getDetailTvShow(id: String): Flow<Resource<TvShowDetail?>>

    fun getFavoriteMovies(): Flow<List<MovieDetail>>

    fun getFavoriteTvShows(): Flow<List<TvShowDetail>>

    fun setFavoriteMovie(detailMovie: MovieDetail, state: Boolean)

    fun setFavoriteTvShow(detailTvShow: TvShowDetail, state: Boolean)

    suspend fun getSearchMovieResult(query: String) : List<Movie>

    suspend fun getSearchTvShowResult(query: String) : List<TvShow>

    suspend fun getSearchNameFavoriteTvShowResult(query: String) : List<TvShowDetail>

    suspend fun getSearchNameFavoriteMovieResult(query: String) : List<MovieDetail>
}