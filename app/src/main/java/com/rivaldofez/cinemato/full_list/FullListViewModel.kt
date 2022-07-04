package com.rivaldofez.cinemato.full_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.usecase.CinemaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class FullListViewModel(private val cinemaUseCase: CinemaUseCase): ViewModel() {
    fun getPopularMovies(page: String): LiveData<Resource<List<Movie>>> {
        return cinemaUseCase.getPopularMovies(page).asLiveData()
    }

    fun getTopRatedMovies(page: String): LiveData<Resource<List<Movie>>> {
        return cinemaUseCase.getTopRatedMovies(page).asLiveData()
    }

    fun getUpComingMovies(page: String): LiveData<Resource<List<Movie>>> {
        return cinemaUseCase.getUpComingMovies(page).asLiveData()
    }

    fun getNowPlayingMovies(page: String): LiveData<Resource<List<Movie>>> {
        return cinemaUseCase.getNowPlayingMovies(page).asLiveData()
    }

    fun getPopularTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getPopularTvShow(page).asLiveData()
    }

    fun getTopRatedTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getTopRatedTvShow(page).asLiveData()
    }

    fun getOnTheAirTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getOnTheAirTvShow(page).asLiveData()
    }

    fun getAiringTodayTvShow(page: String): LiveData<Resource<List<TvShow>>> {
        return cinemaUseCase.getAiringTodayTvShow(page).asLiveData()
    }
}