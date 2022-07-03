package com.rivaldofez.core.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.local.entity.tvshow.*


@Database(entities = [
    MovieItemLocalEntity::class,
    MovieDetailLocalEntity::class,
    PopularMovieLocalEntity::class,
    TopRatedMovieLocalEntity::class,
    UpcomingMovieLocalEntity::class,
    NowPlayingMovieLocalEntity::class,
    TvShowItemLocalEntity::class,
    TvShowDetailLocalEntity::class,
    PopularTvShowLocalEntity::class,
    TopRatedTvShowLocalEntity::class,
    OnTheAirTvShowLocalEntity::class,
    AiringTodayTvShowEntity::class],
    version= 1, exportSchema = false)
abstract class CinemaDatabase: RoomDatabase() {
    abstract fun cinemaDao(): CinemaDao
}