package com.rivaldofez.core.datasource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rivaldofez.core.datasource.local.entity.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.MovieItemLocalEntity
import com.rivaldofez.core.datasource.local.entity.TvShowDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.TvShowItemLocalEntity


@Database(entities = [MovieItemLocalEntity::class, MovieDetailLocalEntity::class, TvShowItemLocalEntity::class, TvShowDetailLocalEntity::class], version= 1, exportSchema = false)
abstract class CinemaDatabase: RoomDatabase() {
    abstract fun cinemaDao(): CinemaDao
}