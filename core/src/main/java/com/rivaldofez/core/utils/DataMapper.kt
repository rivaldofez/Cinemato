package com.rivaldofez.core.utils

import com.rivaldofez.core.datasource.local.entity.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.MovieItemLocalEntity
import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.datasource.remote.response.subresponse.GenresItem
import com.rivaldofez.core.datasource.remote.response.subresponse.SpokenLanguagesItem
import com.rivaldofez.core.domain.model.Movie

object DataMapper {
    private fun mapListGenreToString(input: List<GenresItem>) : String =
        input.joinToString { it.name }

    private fun mapListSpokenLanguageToString(input: List<SpokenLanguagesItem>) : String =
        input.joinToString { it.englishName }

    fun mapMovieListResponseToLocal(input: List<MovieListItem>): List<MovieItemLocalEntity> {
        val movieList = ArrayList<MovieItemLocalEntity>()
        input.map {
            val movie = MovieItemLocalEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapDetailMovieResponseToLocal(input: MovieDetailResponse): MovieDetailLocalEntity =
        MovieDetailLocalEntity(
            id = input.id,
            originalTitle = input.originalLanguage,
            originalLanguage = input.originalLanguage,
            imdbId = input.imdbId,
            title = input.title,
            backdropPath = input.backdropPath,
            revenue = input.revenue,
            genres = mapListGenreToString(input.genres),
            popularity = input.popularity,
            voteCount = input.voteCount,
            budget = input.budget,
            overview = input.overview,
            runtime = input.runtime,
            posterPath = input.posterPath,
            spokenLanguages = mapListSpokenLanguageToString(input.spokenLanguages),
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            belongsToCollection = input.belongsToCollection,
            tagline = input.tagline,
            adult = input.adult,
            homepage = input.homepage,
            status = input.status
        )

    fun mapMovieListLocalToDomain(input: List<MovieItemLocalEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage
            )
        }
}