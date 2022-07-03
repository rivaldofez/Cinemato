package com.rivaldofez.core.datasource.local

import com.rivaldofez.core.datasource.local.entity.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.MovieItemLocalEntity
import com.rivaldofez.core.datasource.local.entity.PopularMovieLocalEntity
import com.rivaldofez.core.datasource.local.entity.TvShowItemLocalEntity
import com.rivaldofez.core.datasource.local.room.CinemaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val cinemaDao: CinemaDao) {
    fun getPopularMovies() : Flow<List<MovieItemLocalEntity>> = cinemaDao.getPopularMovies()

    fun getDetailMovie(id: String): Flow<MovieDetailLocalEntity?> = cinemaDao.getDetailMovie(id.toInt())

    fun getPopularTvShow(): Flow<List<TvShowItemLocalEntity>> = cinemaDao.getPopularTvShow()

    suspend fun insertIdPopularMovies(idPopularMovies: List<PopularMovieLocalEntity>) = cinemaDao.insertIdPopularMovies(idPopularMovies)

    suspend fun insertPopularTvShow(popularTvShows: List<TvShowItemLocalEntity>) = cinemaDao.insertPopularTvShow(popularTvShows)

    suspend fun insertPopularMovies(popularMovies: List<MovieItemLocalEntity>) = cinemaDao.insertPopularMovies(popularMovies)
}