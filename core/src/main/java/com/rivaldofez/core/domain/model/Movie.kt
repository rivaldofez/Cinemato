package com.rivaldofez.core.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    var backdropPath: String? ="",
    val releaseDate: String,
    val popularity: Double,
    val voteAverage: Double,
)