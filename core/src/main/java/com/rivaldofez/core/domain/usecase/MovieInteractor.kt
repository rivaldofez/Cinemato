package com.rivaldofez.core.domain.usecase

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getPopularMovies(page: String): Flow<Resource<List<Movie>>> = movieRepository.getPopularMovies(page)

    override fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>> = movieRepository.getDetailMovie(id = id)
}