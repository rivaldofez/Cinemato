package com.rivaldofez.core.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TvShow (
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val originalName: String,
    val popularity: Double,
    val voteAverage: Double,
)