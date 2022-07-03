package com.rivaldofez.core.domain.model

data class TvShow (
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String,
    val firstAirDate: String,
    val popularity: Double,
    val voteAverage: Double,
)