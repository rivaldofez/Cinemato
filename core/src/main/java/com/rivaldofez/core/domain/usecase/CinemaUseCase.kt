package com.rivaldofez.core.domain.usecase

import com.rivaldofez.core.datasource.Resource
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

    fun getDetailTvShow(id:String): Flow<Resource<TvShowDetail>>

}