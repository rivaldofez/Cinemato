package com.rivaldofez.core.domain.usecase

import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.repository.ICinemaRepository
import kotlinx.coroutines.flow.Flow

class CinemaInteractor(private val cinemaRepository: ICinemaRepository): CinemaUseCase {
    override fun getPopularMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getPopularMovies(page)

    override fun getUpComingMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getUpComingMovies(page)

    override fun getTopRatedMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getTopRatedMovies(page)

    override fun getNowPlayingMovies(page: String): Flow<Resource<List<Movie>>> = cinemaRepository.getNowPlayingMovies(page)

    override fun getDetailMovie(id: String): Flow<Resource<MovieDetail?>> = cinemaRepository.getDetailMovie(id = id)


}