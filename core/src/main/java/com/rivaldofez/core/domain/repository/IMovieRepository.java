package com.rivaldofez.core.domain.repository;

interface IMovieRepository {
    fun getPopularMovies(): Flow<Resource>
}
