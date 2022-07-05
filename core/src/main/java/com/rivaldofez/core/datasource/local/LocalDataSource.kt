package com.rivaldofez.core.datasource.local

import androidx.room.Query
import androidx.sqlite.db.SimpleSQLiteQuery
import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.local.entity.tvshow.*
import com.rivaldofez.core.datasource.local.room.CinemaDao
import com.rivaldofez.core.datasource.remote.MoviesType
import com.rivaldofez.core.datasource.remote.TvShowsType
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.utils.MovieDataMapper
import com.rivaldofez.core.utils.TvShowDataMapper
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val cinemaDao: CinemaDao) {

    suspend fun insertMovieList(type: MoviesType, movieItemList: List<MovieItemLocalEntity>) {
        cinemaDao.insertMovieList(movieItemList)
        when(type){
            MoviesType.Popular -> cinemaDao.insertIdPopularMovies(MovieDataMapper.mapMovieListLocalToPopularId(movieItemList))
            MoviesType.TopRated -> cinemaDao.insertIdTopRatedMovies(MovieDataMapper.mapMovieListLocalToTopRatedId(movieItemList))
            MoviesType.NowPlaying -> cinemaDao.insertIdNowPlayingMovies(MovieDataMapper.mapMovieListLocalToNowPlayingId(movieItemList))
            MoviesType.UpComing -> cinemaDao.insertIdUpcomingMovies(MovieDataMapper.mapMovieListLocalToUpComingId(movieItemList))
        }
    }

    fun getMovieList(type: MoviesType): Flow<List<MovieItemLocalEntity>> {
        val additionQueryString = when(type) {
            MoviesType.Popular -> "popularMovies"
            MoviesType.TopRated -> "topratedmovies"
            MoviesType.UpComing -> "upcomingmovies"
            MoviesType.NowPlaying -> "nowplayingmovies"
        }
        val query = SimpleSQLiteQuery("Select * FROM movielist natural join " + additionQueryString)
        return cinemaDao.getMovieList(query)
    }

    fun getDetailMovie(id: String): Flow<MovieDetailLocalEntity?> = cinemaDao.getDetailMovie(id.toInt())

    suspend fun insertTvShowList(type: TvShowsType, tvShowItemList: List<TvShowItemLocalEntity>){
        cinemaDao.insertTvShowList(tvShowItemList)
        when(type){
            TvShowsType.Popular -> cinemaDao.insertIdPopularTvShow(TvShowDataMapper.mapTvShowListLocalToPopularId(tvShowItemList))
            TvShowsType.TopRated -> cinemaDao.insertIdTopRatedTvShow(TvShowDataMapper.mapTvShowListLocalToTopRatedId(tvShowItemList))
            TvShowsType.OnTheAir -> cinemaDao.insertIdOnTheAirTvShow(TvShowDataMapper.mapTvShowListLocalToOnTheAirId(tvShowItemList))
            TvShowsType.AiringToday -> cinemaDao.insertIdAiringTodayTvShow(TvShowDataMapper.mapTvShowListLocalToAiringTodayId(tvShowItemList))
        }
    }

    fun getTvShowList(type: TvShowsType) : Flow<List<TvShowItemLocalEntity>> {
        val additionQueryString = when(type){
            TvShowsType.Popular -> "populartvshow"
            TvShowsType.TopRated -> "topratedtvshow"
            TvShowsType.AiringToday -> "airingtodaytvshow"
            TvShowsType.OnTheAir -> "ontheairtvshow"
        }
        val query = SimpleSQLiteQuery("Select * FROM tvshowlist natural join " + additionQueryString)
        return cinemaDao.getTvShowList(query)
    }

    fun getDetailTvShow(id: String): Flow<TvShowDetailLocalEntity?> = cinemaDao.getDetailTvShow(id.toInt())


    suspend fun insertDetailTvShow(tvShowDetail: TvShowDetailLocalEntity) = cinemaDao.insertTvShowDetail(tvShowDetail)

    suspend fun insertDetailMovie(movieDetail: MovieDetailLocalEntity) = cinemaDao.insertDetailMovie(movieDetail)

    fun getFavoriteMovies() : Flow<List<MovieDetailLocalEntity>> = cinemaDao.getFavoritoMovies()

    fun getFavoriteTvShow(): Flow<List<TvShowDetailLocalEntity>> = cinemaDao.getFavoritoTvShows()

    fun setFavoriteMovie(movieDetail: MovieDetailLocalEntity, newState: Boolean){
        movieDetail.isFavorite = newState
        cinemaDao.updateDetailMovie(movieDetail)
    }

    fun setFavoriteTvShow(tvShowDetail: TvShowDetailLocalEntity, newState: Boolean){
        tvShowDetail.isFavorite = newState
        cinemaDao.updateDetailTvShow(tvShowDetail)
    }

    suspend fun getSearchMovieResult(query: String) = cinemaDao.getSearchMovieResult("%$query%")

    suspend fun getSearchTvShowResult(query: String) = cinemaDao.getSearchTvShowResult("%$query%")

    suspend fun getSearchNameFavoriteTvShowResult(query: String) = cinemaDao.getSearchNameFavoriteTvShowResult("%$query%")

    suspend fun getSearchNameFavoriteMovieResult(query: String) = cinemaDao.getSearchNameFavoriteMovieResult("%$query%")

}