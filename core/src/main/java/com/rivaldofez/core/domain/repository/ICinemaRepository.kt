package com.rivaldofez.core.domain.repository

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.datasource.remote.MoviesType
import com.rivaldofez.core.datasource.remote.TvShowsType
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.datasource.remote.response.MoviesResponse
import com.rivaldofez.core.datasource.remote.response.TvShowListItem
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail
import kotlinx.coroutines.flow.Flow

interface ICinemaRepository {

    fun getMovies(type: MoviesType, page: String): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>>

    fun getTvShows(type: TvShowsType, page: String): Flow<Resource<List<TvShow>>>

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