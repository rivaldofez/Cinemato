package com.rivaldofez.core.domain.repository

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getPopularMovies(page: String): Flow<Resource<List<Movie>>>

    fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>>
}