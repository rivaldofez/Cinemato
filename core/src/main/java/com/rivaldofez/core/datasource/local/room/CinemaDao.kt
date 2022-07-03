package com.rivaldofez.core.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rivaldofez.core.datasource.local.entity.TvShowItemLocalEntity
import com.rivaldofez.core.datasource.local.entity.movie.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CinemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(popularMovies: List<MovieItemLocalEntity>)

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





    @Query("Select * FROM moviedetail where id= :id")
    fun getDetailMovie(id: Int): Flow<MovieDetailLocalEntity?>

    @Query("Select * FROM tvshowlist")
    fun getPopularTvShow(): Flow<List<TvShowItemLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularTvShow(tvShows: List<TvShowItemLocalEntity>)
}