package com.rivaldofez.core.domain.repository

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface ICinemaRepository {

    fun getPopularMovies(page: String): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(page: String): Flow<Resource<List<Movie>>>

    fun getUpComingMovies(page: String): Flow<Resource<List<Movie>>>

    fun getNowPlayingMovies(page: String): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>>
}