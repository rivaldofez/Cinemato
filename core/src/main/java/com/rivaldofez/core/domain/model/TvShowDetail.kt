package com.rivaldofez.core.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TvShowDetail (

    val id: Int,
    val name: String,
    val originalName: String,
    val originalLanguage: String,
    val numberOfEpisodes: Int,
    val type: String,
    val backdropPath: String,
    val popularity: Double,
    val numberOfSeasons: Int,
    val voteCount: Int,
    val firstAirDate: String,
    val overview: String,
    val languages: List<String>,
    val posterPath: String,
    val voteAverage: Double,
    val tagline: String,
    val adult: Boolean,
    val inProduction: Boolean,
    val lastAirDate: String,
    val homepage: String,
    val status: String,
    val genres: String,
    val spokenLanguages: String
)