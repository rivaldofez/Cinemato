package com.rivaldofez.cinemato.detail_tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.TvShowDetail
import com.rivaldofez.core.domain.usecase.CinemaUseCase

class DetailTvShowViewModel(private val cinemaUseCase: CinemaUseCase): ViewModel() {
    fun getDetailTvShow(id: String): LiveData<Resource<TvShowDetail?>> {
        return cinemaUseCase.getDetailTvShow(id).asLiveData()
    }

    fun setFavoriteTvShow(tvShowDetail: TvShowDetail, state: Boolean) = cinemaUseCase.sset
}