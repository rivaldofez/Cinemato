package com.rivaldofez.cinemato.detail_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.usecase.CinemaUseCase

class DetailMovieViewModel(private val cinemaUseCase: CinemaUseCase): ViewModel() {
    fun getDetailMovie(id: String): LiveData<Resource<MovieDetail?>> {
        return cinemaUseCase.getDetailMovie(id).asLiveData()
    }

    fun setFavoriteMovie(movieDetail: MovieDetail, state: Boolean) = cinemaUseCase.setFavoriteMovie(movieDetail, state)
}