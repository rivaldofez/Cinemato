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

    suspend fun insertMovieList(type: MoviesType, page: String, movieItemList: List<MovieItemLocalEntity>) {
        cinemaDao.insertMovieList(movieItemList)
        when(type){
            MoviesType.Popular -> cinemaDao.insertIdPopularMovies(MovieDataMapper.mapMovieListLocalToPopularId(movieItemList, page))
            MoviesType.TopRated -> cinemaDao.insertIdTopRatedMovies(MovieDataMapper.mapMovieListLocalToTopRatedId(movieItemList, page))
            MoviesType.NowPlaying -> cinemaDao.insertIdNowPlayingMovies(MovieDataMapper.mapMovieListLocalToNowPlayingId(movieItemList, page))
            MoviesType.UpComing -> cinemaDao.insertIdUpcomingMovies(MovieDataMapper.mapMovieListLocalToUpComingId(movieItemList, page))
        }
    }

    fun getMovieList(type: MoviesType, page: String): Flow<List<MovieItemLocalEntity>> {
        val additionQueryString = when(type) {
            MoviesType.Popular -> "popularMovies"
            MoviesType.TopRated -> "topratedmovies"
            MoviesType.UpComing -> "upcomingmovies"
            MoviesType.NowPlaying -> "nowplayingmovies"
        }
        val query = SimpleSQLiteQuery("Select * FROM movielist natural join " + additionQueryString + " WHERE page = " + page)
        return cinemaDao.getMovieList(query)
    }

    fun getDetailMovie(id: String): Flow<MovieDetailLocalEntity?> = cinemaDao.getDetailMovie(id.toInt())




    suspend fun insertTvShowList(type: TvShowsType,page: String, tvShowItemList: List<TvShowItemLocalEntity>){
        cinemaDao.insertTvShowList(tvShowItemList)
        when(type){
            TvShowsType.Popular -> cinemaDao.insertIdPopularTvShow(TvShowDataMapper.mapTvShowListLocalToPopularId(tvShowItemList, page))
            TvShowsType.TopRated -> cinemaDao.insertIdTopRatedTvShow(TvShowDataMapper.mapTvShowListLocalToTopRatedId(tvShowItemList, page))
            TvShowsType.OnTheAir -> cinemaDao.insertIdOnTheAirTvShow(TvShowDataMapper.mapTvShowListLocalToOnTheAirId(tvShowItemList, page))
            TvShowsType.AiringToday -> cinemaDao.insertIdAiringTodayTvShow(TvShowDataMapper.mapTvShowListLocalToAiringTodayId(tvShowItemList, page))
        }
    }

    fun getTvShowList(type: TvShowsType, page: String) : Flow<List<TvShowItemLocalEntity>> {
        val additionQueryString = when(type){
            TvShowsType.Popular -> "populartvshow"
            TvShowsType.TopRated -> "topratedtvshow"
            TvShowsType.AiringToday -> "airingtodaytvshow"
            TvShowsType.OnTheAir -> "ontheairtvshow"
        }
        val query = SimpleSQLiteQuery("Select * FROM tvshowlist natural join " + additionQueryString + " WHERE page = " + page)
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