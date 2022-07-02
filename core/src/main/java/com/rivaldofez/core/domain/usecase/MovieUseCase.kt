package com.rivaldofez.core.domain.usecase

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getPopularMovies(page: String): Flow<Resource<List<Movie>>>
    fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>>

}