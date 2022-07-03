package com.rivaldofez.core.utils

import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.datasource.remote.response.subresponse.GenresItem
import com.rivaldofez.core.datasource.remote.response.subresponse.SpokenLanguagesItem
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.MovieDetail

object MovieDataMapper {
    fun mapMovieListResponseToPopularId(input: List<MovieListItem>): List<PopularMovieLocalEntity> {
        val idList = ArrayList<PopularMovieLocalEntity>()
        input.map {
            val movieId = PopularMovieLocalEntity(id = it.id)
            idList.add(movieId)
        }
        return idList
    }

    fun mapMovieListResponseToTopRatedId(input: List<MovieListItem>): List<TopRatedMovieLocalEntity> {
        val idList = ArrayList<TopRatedMovieLocalEntity>()
        input.map {
            val movieId = TopRatedMovieLocalEntity(id = it.id)
            idList.add(movieId)
        }
        return idList
    }

    fun mapMovieListResponseToUpComingId(input: List<MovieListItem>): List<UpcomingMovieLocalEntity> {
        val idList = ArrayList<UpcomingMovieLocalEntity>()
        input.map {
            val movieId = UpcomingMovieLocalEntity(id = it.id)
            idList.add(movieId)
        }
        return idList
    }

    fun mapMovieListResponseToNowPlayingId(input: List<MovieListItem>): List<NowPlayingMovieLocalEntity> {
        val idList = ArrayList<NowPlayingMovieLocalEntity>()
        input.map {
            val movieId = NowPlayingMovieLocalEntity(id = it.id)
            idList.add(movieId)
        }
        return idList
    }

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
                releaseDate = it.releaseDate
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
            genres = MapperAtribute.mapListGenreToString(input.genres),
            popularity = input.popularity,
            voteCount = input.voteCount,
            budget = input.budget,
            overview = input.overview,
            runtime = input.runtime,
            posterPath = input.posterPath,
            spokenLanguages = MapperAtribute.mapListSpokenLanguageToString(input.spokenLanguages),
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            belongsToCollection = input.belongsToCollection,
            tagline = input.tagline,
            adult = input.adult,
            homepage = input.homepage,
            status = input.status
        )

    fun mapDetailMovieLocalToDomain(input: MovieDetailLocalEntity): MovieDetail{
        val movieDetail = MovieDetail(
            id = input.id,
            title = input.title,
            originalLanguage = input.originalLanguage,
            imdbId = input.imdbId,
            backdropPath = input.backdropPath,
            revenue = input.revenue,
            popularity = input.popularity,
            voteCount = input.voteCount,
            budget = input.budget,
            overview = input.overview,
            originalTitle = input.originalTitle,
            runtime = input.runtime,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            voteAverage = input.voteAverage,
            belongsToCollection = input.belongsToCollection,
            tagline = input.tagline,
            adult = input.adult,
            homepage = input.homepage,
            status = input.status,
            genres = input.genres,
            spokenLanguages = input.spokenLanguages
        )

        return movieDetail
    }

    fun mapMovieListLocalToDomain(input: List<MovieItemLocalEntity>): List<Movie> {
        val movieList = ArrayList<Movie>()
        input.map {
            val movie = Movie(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate
            )
            movieList.add(movie)
        }
        return movieList
    }


    fun mapDetailMovieListLocalToDomain(input: List<MovieDetailLocalEntity>) : List<MovieDetail> =
        input.map { mapDetailMovieLocalToDomain(it) }
}