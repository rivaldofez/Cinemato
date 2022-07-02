package com.rivaldofez.cinemato.di

import com.rivaldofez.cinemato.movie.MovieViewModel
import com.rivaldofez.core.domain.usecase.MovieInteractor
import com.rivaldofez.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel{ MovieViewModel(get()) }
}