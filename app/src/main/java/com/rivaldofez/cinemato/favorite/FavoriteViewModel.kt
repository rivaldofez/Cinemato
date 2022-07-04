package com.rivaldofez.cinemato.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.domain.usecase.CinemaUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


@FlowPreview
@ExperimentalCoroutinesApi
class FavoriteViewModel(private val cinemaUseCase: CinemaUseCase): ViewModel() {
    val favoriteMovie = cinemaUseCase.getFavoriteMovie().asLiveData()

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResultMovieFavorite = queryChannel.asFlow()
        .distinctUntilChanged()
        .debounce(300)
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            cinemaUseCase.getSearchNameFavoriteMovieResult(it)
        }.asLiveData()

    val searchResultTvShowFavorite = queryChannel.asFlow()
        .distinctUntilChanged()
        .debounce(300)
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            cinemaUseCase.getSearchNameFavoriteTvShowResult(it)
        }.asLiveData()

    fun setFavoriteMovie(movieDetail: MovieDetail, state: Boolean) = cinemaUseCase.setFavoriteMovie(movieDetail, state)
}