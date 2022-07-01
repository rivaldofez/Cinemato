package com.rivaldofez.core.datasource.local.room

import androidx.room.Database
import com.rivaldofez.core.datasource.local.entity.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.MovieItemLocalEntity


@Database(entities = [MovieItemLocalEntity::class, MovieDetailLocalEntity::class], version= 1, exportSchema = false)
abstract class MovieDatabase {
    abstract fun movieDao(): MovieDao
}