package com.rivaldofez.cinemato.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class MovieViewModel(private val movieUseCase: MovieUseCase): ViewModel() {
//    val popularMovies = movieUseCase.getPopularMovies().asLiveData()

    fun getPopularMovies(page: String): LiveData<Resource<List<Movie>>>{
        return  movieUseCase.getPopularMovies(page).asLiveData()
    }
    fun hello(){
        print("Hello")
    }
}