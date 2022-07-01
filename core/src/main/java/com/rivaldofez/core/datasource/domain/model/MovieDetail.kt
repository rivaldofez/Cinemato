package com.rivaldofez.core.datasource.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class MovieDetail {
    val id: Int,
    val originalLanguage: String,
    val imdbId: String,
    val video: Boolean,
    val title: String,
//    val backdropPath: String,
//    val revenue: Int,
//    val popularity: Double,
    val voteCount: Int,
//    val budget: Int,
//    val overview: String,
//    val originalTitle: String,
//    val runtime: Int,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val belongsToCollection: String,
    val tagline: String,
    val adult: Boolean,
    val homepage: String,
//    val status: String
}