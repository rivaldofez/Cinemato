package com.rivaldofez.core.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldofez.core.datasource.local.entity.*


@Database(entities = [
    MovieItemLocalEntity::class,
    MovieDetailLocalEntity::class,
    TvShowItemLocalEntity::class,
    TvShowDetailLocalEntity::class,
    PopularMovieLocalEntity::class
                     ], version= 1, exportSchema = false)
abstract class CinemaDatabase: RoomDatabase() {
    abstract fun cinemaDao(): CinemaDao
}