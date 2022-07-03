package com.rivaldofez.core.datasource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rivaldofez.core.datasource.local.entity.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.MovieItemLocalEntity
import com.rivaldofez.core.datasource.local.entity.TvShowItemLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("Select * FROM movielist")
    fun getPopularMovies(): Flow<List<MovieItemLocalEntity>>

    @Query("Select * FROM moviedetail where id= :id")
    fun getDetailMovie(id: Int): Flow<MovieDetailLocalEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(popularMovies: List<MovieItemLocalEntity>)

    @Query("Select * FROM tvshowlist")
    fun getPopularTvShow(): Flow<List<TvShowItemLocalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularTvShow(tvShows: List<TvShowItemLocalEntity>)
}