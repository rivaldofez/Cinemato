package com.rivaldofez.core.utils

import com.rivaldofez.core.datasource.local.entity.movie.MovieDetailLocalEntity
import com.rivaldofez.core.datasource.local.entity.tvshow.*
import com.rivaldofez.core.datasource.remote.response.TvShowDetailResponse
import com.rivaldofez.core.datasource.remote.response.TvShowListItem
import com.rivaldofez.core.domain.model.MediatorItem
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.domain.model.TvShowDetail

object TvShowDataMapper {
    fun mapTvShowListResponseToPopularId(input: List<TvShowListItem>): List<PopularTvShowLocalEntity> {
        val idList = ArrayList<PopularTvShowLocalEntity>()
        input.map {
            val tvShowId = PopularTvShowLocalEntity(id = it.id)
            idList.add(tvShowId)
        }
        return idList
    }

    fun mapTvShowListResponseToTopRatedId(input: List<TvShowListItem>): List<TopRatedTvShowLocalEntity> {
        val idList = ArrayList<TopRatedTvShowLocalEntity>()
        input.map {
            val tvShowId = TopRatedTvShowLocalEntity(id = it.id)
            idList.add(tvShowId)
        }
        return idList
    }

    fun mapTvShowListResponseToOnTheAirId(input: List<TvShowListItem>): List<OnTheAirTvShowLocalEntity> {
        val idList = ArrayList<OnTheAirTvShowLocalEntity>()
        input.map {
            val tvShowId = OnTheAirTvShowLocalEntity(id = it.id)
            idList.add(tvShowId)
        }
        return idList
    }

    fun mapTvShowListResponseToAiringTodayId(input: List<TvShowListItem>): List<AiringTodayTvShowEntity> {
        val idList = ArrayList<AiringTodayTvShowEntity>()
        input.map {
            val tvShowId = AiringTodayTvShowEntity(id = it.id)
            idList.add(tvShowId)
        }
        return idList
    }

    fun mapTvShowListResponseToLocal(input: List<TvShowListItem>): List<TvShowItemLocalEntity> {
        val tvShowList = ArrayList<TvShowItemLocalEntity>()
        input.map {
            val tvshow = TvShowItemLocalEntity(
                id = it.id,
                name = it.name,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath ?: "",
                firstAirDate = it.firstAirDate,
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
            backdropPath = input.backdropPath ?: "",
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
            backdropPath = input.backdropPath ?: "",
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
            spokenLanguages = input.spokenLanguages,
            isFavorite = input.isFavorite
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
                backdropPath = it.backdropPath ?: "",
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                firstAirDate = it.firstAirDate
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapDetailTvShowListLocalToDomain(input: List<TvShowDetailLocalEntity>): List<TvShowDetail> =
        input.map { mapDetailTvShowLocalToDomain(it) }


    fun mapDomainDetailTvShowToLocal(input: TvShowDetail): TvShowDetailLocalEntity {
        return TvShowDetailLocalEntity(
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
            spokenLanguages = input.spokenLanguages,
            isFavorite = input.isFavorite
        )
    }

    fun mapTvShowToMediatorItem(input: TvShow): MediatorItem {
        return MediatorItem(
            id = input.id,
            title = input.name,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            releaseDate = input.firstAirDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage,
            type = "tvshow"
        )
    }

    fun mapMediatorItemToTvShow(input: MediatorItem): TvShow{
        return TvShow(
            id = input.id,
            name = input.title,
            posterPath = input.posterPath,
            backdropPath = input.backdropPath,
            firstAirDate = input.releaseDate,
            popularity = input.popularity,
            voteAverage = input.voteAverage
        )
    }

    fun mapListTvShowToMediatorItem(input: List<TvShow>): List<MediatorItem>{
        val mediatorItemList = ArrayList<MediatorItem>()
        input.map {
            mediatorItemList.add(TvShowDataMapper.mapTvShowToMediatorItem(it))
        }
        return mediatorItemList
    }
}

