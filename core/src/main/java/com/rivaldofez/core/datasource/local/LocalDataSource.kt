package com.rivaldofez.core.datasource.local

import com.rivaldofez.core.datasource.local.entity.TvShowItemLocalEntity
import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.local.room.CinemaDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val cinemaDao: CinemaDao) {
    suspend fun insertMovieList(movieItemList: List<MovieItemLocalEntity>) = cinemaDao.insertMovieList(movieItemList)

    suspend fun insertIdPopularMovies(idPopularMovies: List<PopularMovieLocalEntity>) = cinemaDao.insertIdPopularMovies(idPopularMovies)

    suspend fun insertIdTopRatedMovies(idTopRatedMovies: List<TopRatedMovieLocalEntity>) = cinemaDao.insertIdTopRatedMovies(idTopRatedMovies)

    suspend fun insertIdNowPlayingMovies(idNowPlayingMovies: List<NowPlayingMovieLocalEntity>) = cinemaDao.insertIdNowPlayingMovies(idNowPlayingMovies)

    suspend fun insertIdUpComingMovies(idUpComingMovies: List<UpcomingMovieLocalEntity>) = cinemaDao.insertIdUpcomingMovies(idUpComingMovies)

    fun getPopularMovies() : Flow<List<MovieItemLocalEntity>> = cinemaDao.getPopularMovies()

    fun getTopRatedMovies() : Flow<List<MovieItemLocalEntity>> = cinemaDao.getTopRatedMovies()

    fun getUpComingMovies() : Flow<List<MovieItemLocalEntity>> = cinemaDao.getUpcomingMovies()

    fun getNowPlayingMovies() : Flow<List<MovieItemLocalEntity>> = cinemaDao.getNowPlayingMovies()





    fun getDetailMovie(id: String): Flow<MovieDetailLocalEntity?> = cinemaDao.getDetailMovie(id.toInt())

    fun getPopularTvShow(): Flow<List<TvShowItemLocalEntity>> = cinemaDao.getPopularTvShow()

    suspend fun insertPopularTvShow(popularTvShows: List<TvShowItemLocalEntity>) = cinemaDao.insertPopularTvShow(popularTvShows)


}