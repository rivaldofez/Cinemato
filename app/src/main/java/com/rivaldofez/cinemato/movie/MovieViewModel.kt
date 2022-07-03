package com.rivaldofez.cinemato.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.usecase.CinemaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MovieViewModel(private val cinemaUseCase: CinemaUseCase): ViewModel() {
//    val popularMovies = movieUseCase.getPopularMovies().asLiveData()

    fun getPopularMovies(page: String): LiveData<Resource<List<Movie>>>{
        return cinemaUseCase.getPopularMovies(page).asLiveData()
    }

    fun getTopRatedMovies(page: String): LiveData<Resource<List<Movie>>>{
        return cinemaUseCase.getTopRatedMovies(page).asLiveData()
    }

    fun getUpComingMovies(page: String): LiveData<Resource<List<Movie>>>{
        return cinemaUseCase.getUpComingMovies(page).asLiveData()
    }

    fun getNowPlayingMovies(page: String): LiveData<Resource<List<Movie>>>{
        return cinemaUseCase.getNowPlayingMovies(page).asLiveData()
    }

    fun hello(){
        print("Hello")
    }
}