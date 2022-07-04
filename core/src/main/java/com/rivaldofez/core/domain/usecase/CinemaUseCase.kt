package com.rivaldofez.core.domain.usecase

import androidx.room.Query
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.datasource.local.entity.movie.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.movie.MovieItemLocalEntity
import com.rivaldofez.core.datasource.local.entity.tvshow.TvShowDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.tvshow.TvShowItemLocalEntity
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail
import kotlinx.coroutines.flow.Flow

interface CinemaUseCase {

    fun getPopularMovies(page: String): Flow<Resource<List<Movie>>>

    fun getUpComingMovies(page: String): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(page: String): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovies(page: String): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>>

    fun getPopularTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getTopRatedTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getOnTheAirTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getAiringTodayTvShow(page: String): Flow<Resource<List<TvShow>>>

    fun getDetailTvShow(id:String): Flow<Resource<TvShowDetail?>>

    fun setFavoriteTvShow(detailTvShow: TvShowDetail, state: Boolean)

    fun setFavoriteMovie(detailMovie: MovieDetail, state: Boolean)

    fun getFavoriteTvShow(): Flow<List<TvShowDetail>>

    fun getFavoriteMovie(): Flow<List<MovieDetail>>

    suspend fun getSearchMovieResult(query: String): List<Movie>

    suspend fun getSearchTvShowResult(query: String): List<TvShow>

    suspend fun getSearchNameFavoriteTvShowResult(query: String): List<TvShowDetail>

    suspend fun getSearchNameFavoriteMovieResult(query: String): List<MovieDetail>

}