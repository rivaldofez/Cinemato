package com.rivaldofez.core.datasource.local

import com.rivaldofez.core.datasource.local.entity.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.MovieItemLocalEntity
import com.rivaldofez.core.datasource.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getPopularMovies() : Flow<List<MovieItemLocalEntity>> = movieDao.getPopularMovies()

    fun getDetailMovie(id: String): Flow<MovieDetailLocalEntity?> = movieDao.getDetailMovie(id.toInt())

    suspend fun insertPopularMovies(popularMovies: List<MovieItemLocalEntity>) = movieDao.insertPopularMovies(popularMovies)
}