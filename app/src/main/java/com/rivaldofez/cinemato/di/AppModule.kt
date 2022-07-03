package com.rivaldofez.cinemato.di

import com.rivaldofez.cinemato.detail_movie.DetailMovieViewModel
import com.rivaldofez.cinemato.movie.MovieViewModel
import com.rivaldofez.cinemato.tvshow.TvShowViewModel
import com.rivaldofez.core.domain.usecase.CinemaInteractor
import com.rivaldofez.core.domain.usecase.CinemaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CinemaUseCase> { CinemaInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}