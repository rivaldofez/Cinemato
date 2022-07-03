package com.rivaldofez.cinemato.movie

import com.rivaldofez.core.domain.model.Movie

interface MovieCallback {
    fun onMovieItemClick(movie: Movie)
}