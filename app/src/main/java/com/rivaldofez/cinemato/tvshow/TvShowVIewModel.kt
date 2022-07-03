package com.rivaldofez.cinemato.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.usecase.CinemaUseCase

class TvShowViewModel(private val cinemaUseCase: CinemaUseCase): ViewModel() {
    fun getPopularTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getPopularTvShow(page).asLiveData()
    }

    fun getTopRatedTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getPopularTvShow(page).asLiveData()
    }

    fun getOnTheAirTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getPopularTvShow(page).asLiveData()
    }

    fun getAiringTodayTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getPopularTvShow(page).asLiveData()
    }
}