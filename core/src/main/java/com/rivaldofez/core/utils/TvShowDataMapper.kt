package com.rivaldofez.core.utils

import com.rivaldofez.core.datasource.local.entity.tvshow.TvShowDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.tvshow.TvShowItemLocalEntity
import com.rivaldofez.core.datasource.remote.response.TvShowDetailResponse
import com.rivaldofez.core.datasource.remote.response.TvShowListItem
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail

object TvShowDataMapper {
    fun mapTvShowListResponseToLocal(input: List<TvShowListItem>): List<TvShowItemLocalEntity> {
        val tvShowList = ArrayList<TvShowItemLocalEntity>()
        input.map {
            val tvshow = TvShowItemLocalEntity(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                firstAirDate = it.backdropPath,
                overview = it.overview,
                popularity = it.popularity,
                voteAverage = it.voteAverage
            )
            tvShowList.add(tvshow)
        }
        return tvShowList
    }

    fun mapDetailTvShowResponseToLocal(input: TvShowDetailResponse): TvShowDetailLocalEntity=
        TvShowDetailLocalEntity(
            id = input.id,
            name = input.name,
            originalName = input.originalName,
            originalLanguage = input.originalLanguage,
            numberOfEpisodes = input.numberOfEpisodes,
            type = input.type,
            backdropPath = input.backdropPath,
            popularity = input.popularity,
            numberOfSeasons = input.numberOfSeasons,
            voteCount = input.voteCount,
            firstAirDate = input.firstAirDate,
            overview = input.overview,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            tagline = input.tagline,
            adult = input.adult,
            inProduction = input.inProduction,
            lastAirDate = input.lastAirDate,
            homepage = input.homepage,
            status = input.status,
            genres = MapperAtribute.mapListGenreToString(input.genres),
            spokenLanguages = MapperAtribute.mapListSpokenLanguageToString(input.spokenLanguages)
        )

    fun mapDetailTvShowLocalToDomain(input: TvShowDetailLocalEntity): TvShowDetail{
        val tvShowDetail = TvShowDetail(
            id = input.id,
            name = input.name,
            originalName = input.originalName,
            originalLanguage = input.originalLanguage,
            numberOfEpisodes = input.numberOfEpisodes,
            type = input.type,
            backdropPath = input.backdropPath,
            popularity = input.popularity,
            numberOfSeasons = input.numberOfSeasons,
            voteCount = input.voteCount,
            firstAirDate = input.firstAirDate,
            overview = input.overview,
            posterPath = input.posterPath,
            voteAverage = input.voteAverage,
            tagline = input.tagline,
            adult = input.adult,
            inProduction = input.inProduction,
            lastAirDate = input.lastAirDate,
            homepage = input.homepage,
            status = input.status,
            genres = input.genres,
            spokenLanguages = input.spokenLanguages
        )
        return tvShowDetail
    }

    fun mapTvShowListLocalToDomain(input: List<TvShowItemLocalEntity>): List<TvShow>{
        val tvShowList = ArrayList<TvShow>()
        input.map {
            val tvShow = TvShow(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                firstAirDate = it.firstAirDate
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapDetailTvShowListLocalToDomain(input: List<TvShowItemLocalEntity>): List<TvShowDetail> =
        input.map { mapDetailTvShowLocalToDomain(it) }
}
