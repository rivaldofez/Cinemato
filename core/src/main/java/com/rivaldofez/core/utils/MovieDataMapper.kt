package com.rivaldofez.core.utils

import android.provider.MediaStore
import com.rivaldofez.core.datasource.local.entity.movie.*
import com.rivaldofez.core.datasource.remote.response.MovieDetailResponse
import com.rivaldofez.core.datasource.remote.response.MovieListItem
import com.rivaldofez.core.domain.model.MediatorItem
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
                backdropPath = it.backdropPath ?: "",
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
            originalTitle = input.originalTitle,
            originalLanguage = input.originalLanguage,
            imdbId = input.imdbId,
            title = input.title,
            backdropPath = input.backdropPath ?: "",
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
            backdropPath = input.backdropPath ?: "",
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
            tagline = input.tagline,
            adult = input.adult,
            homepage = input.homepage,
            status = input.status,
            genres = input.genres,
            spokenLanguages = input.spokenLanguages,
            isFavorite = input.isFavorite

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
                backdropPath = it.backdropPath ?: "",
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

    fun mapDomainDetailMovieToLocal(input: MovieDetail): MovieDetailLocalEntity {
        return MovieDetailLocalEntity(
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
            tagline = input.tagline,
            adult = input.adult,
            homepage = input.homepage,
            status = input.status,
            genres = input.genres,
            spokenLanguages = input.spokenLanguages,
            isFavorite = input.isFavorite
        )
    }

    fun mapMovieDataToMediator(input: Movie): MediatorItem{
        return MediatorItem(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath ,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            type = "movie"
        )
    }

    fun mapMediatorItemToMovie(input: MediatorItem): Movie {
        return Movie(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
        )
    }

    fun mapListMovieToMediatorItem(input: List<Movie>): List<MediatorItem>{
        val mediatorItemList = ArrayList<MediatorItem>()
        input.map {
            mediatorItemList.add(mapMovieDataToMediator(it))
        }
        return mediatorItemList
    }

    fun mapDomainDetailMovieToMediatorItem(input: MovieDetail): MediatorItem {
        return MediatorItem(
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            type = "movie"
        )
    }

    fun mapListDomainDetailMovieToMediatorItem(input: List<MovieDetail>): List<MediatorItem>{
        val mediatorItemList = ArrayList<MediatorItem>()
        input.map {
            mediatorItemList.add(mapDomainDetailMovieToMediatorItem(it))
        }
        return mediatorItemList
    }
}