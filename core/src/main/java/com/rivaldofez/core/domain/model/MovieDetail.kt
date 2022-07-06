package com.rivaldofez.core.domain.model

import kotlinx.coroutines.flow.Flow

data class MovieDetail(
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val imdbId: String,
    var backdropPath: String? = "",
    val revenue: Int,
    val popularity: Double,
    val voteCount: Int,
    val budget: Int,
    val overview: String,
    val originalTitle: String,
    val runtime: Int,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val tagline: String,
    val adult: Boolean,
    val homepage: String,
    val status: String,
    val genres: String,
    val spokenLanguages: String,
    val isFavorite: Boolean
)