package com.rivaldofez.core.datasource.local.room

import androidx.room.*
import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.local.entity.tvshow.*
import com.rivaldofez.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface CinemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieItemList: List<MovieItemLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdPopularMovies(idPopularMovies: List<PopularMovieLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdTopRatedMovies(idTopRatedMovies: List<TopRatedMovieLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdNowPlayingMovies(idNowPlayingMovies: List<NowPlayingMovieLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdUpcomingMovies(idUpcomingMovies: List<UpcomingMovieLocalEntity>)

    @Query("Select * FROM movielist natural join popularMovies")
    fun getPopularMovies(): Flow<List<MovieItemLocalEntity>>

    @Query("Select * FROM movielist natural join topratedmovies")
    fun getTopRatedMovies(): Flow<List<MovieItemLocalEntity>>

    @Query("Select * FROM movielist natural join upcomingmovies")
    fun getUpcomingMovies(): Flow<List<MovieItemLocalEntity>>

    @Query("Select * FROM movielist natural join nowplayingmovies")
    fun getNowPlayingMovies(): Flow<List<MovieItemLocalEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowList(tvShowItemList: List<TvShowItemLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdPopularTvShow(idPopularTvShow: List<PopularTvShowLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdTopRatedTvShow(idTopRatedTvShow: List<TopRatedTvShowLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdOnTheAirTvShow(idOnAirTvShow: List<OnTheAirTvShowLocalEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIdAiringTodayTvShow(idAiringTvShow: List<AiringTodayTvShowEntity>)

    @Query("Select * FROM tvshowlist natural join populartvshow")
    fun getPopularTvShow(): Flow<List<TvShowItemLocalEntity>>

    @Query("Select * FROM tvshowlist natural join topratedtvshow")
    fun getTopRatedTvShow(): Flow<List<TvShowItemLocalEntity>>

    @Query("Select * FROM tvshowlist natural join ontheairtvshow")
    fun getOnTheAirShow(): Flow<List<TvShowItemLocalEntity>>

    @Query("Select * FROM tvshowlist natural join airingtodaytvshow")
    fun getAiringTodayTvShow(): Flow<List<TvShowItemLocalEntity>>


    @Query("Select * FROM moviedetail where id= :id")
    fun getDetailMovie(id: Int): Flow<MovieDetailLocalEntity?>

    @Query("Select * FROM tvshowdetail where id= :id")
    fun getDetailTvShow(id: Int): Flow<TvShowDetailLocalEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(detailMovie: MovieDetailLocalEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowDetail(detailTvShow: TvShowDetailLocalEntity)

    @Query("Select * From moviedetail where isFavorite = 1")
    fun getFavoritoMovies(): Flow<List<MovieDetailLocalEntity>>

    @Query("Select * From tvshowdetail where isFavorite = 1")
    fun getFavoritoTvShows(): Flow<List<TvShowDetailLocalEntity>>

    @Update
    fun updateDetailMovie(detailMovie: MovieDetailLocalEntity)

    @Update
    fun updateDetailTvShow(detailTvShow: TvShowDetailLocalEntity)


}