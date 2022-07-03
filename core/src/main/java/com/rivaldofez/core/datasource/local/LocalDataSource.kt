package com.rivaldofez.core.datasource.local

import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.local.entity.tvshow.*
import com.rivaldofez.core.datasource.local.room.CinemaDao
import com.rivaldofez.core.domain.model.MovieDetail
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


    suspend fun insertTvShowList(tvShowItemList: List<TvShowItemLocalEntity>) = cinemaDao.insertTvShowList(tvShowItemList)

    suspend fun insertIdPopularTvShow(idPopularTvShow: List<PopularTvShowLocalEntity>) = cinemaDao.insertIdPopularTvShow(idPopularTvShow)

    suspend fun insertIdTopRatedTvShow(idTopRatedTvShow: List<TopRatedTvShowLocalEntity>) = cinemaDao.insertIdTopRatedTvShow(idTopRatedTvShow)

    suspend fun insertIdOnTheAIrTvShow(idOnTheAirTvShow: List<OnTheAirTvShowLocalEntity>) = cinemaDao.insertIdOnTheAirTvShow(idOnTheAirTvShow)

    suspend fun insertIdAiringTodayTvShow(idAiringTodayTvShow: List<AiringTodayTvShowEntity>) = cinemaDao.insertIdAiringTodayTvShow(idAiringTodayTvShow)

    fun getPopularTvShow() : Flow<List<TvShowItemLocalEntity>> = cinemaDao.getPopularTvShow()

    fun getTopRatedTvShow() : Flow<List<TvShowItemLocalEntity>> = cinemaDao.getTopRatedTvShow()

    fun getOnTheAirTvShow() : Flow<List<TvShowItemLocalEntity>> = cinemaDao.getOnTheAirShow()

    fun getAiringTodayTvShow() : Flow<List<TvShowItemLocalEntity>> = cinemaDao.getAiringTodayTvShow()

    fun getDetailTvShow(id: String): Flow<TvShowDetailLocalEntity?> = cinemaDao.getDetailTvShow(id.toInt())


    suspend fun insertDetailTvShow(tvShowDetail: TvShowDetailLocalEntity) = cinemaDao.insertTvShowDetail(tvShowDetail)

    suspend fun insertDetailMovie(movieDetail: MovieDetailLocalEntity) = cinemaDao.insertDetailMovie(movieDetail)


}