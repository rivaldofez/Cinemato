package com.rivaldofez.core.domain.model

data class Movie (
    val id: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val popularity: Double,
    val voteAverage: Double,
)