package com.rivaldofez.core.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class TvShowDetail (

    val id: Int,
    val originalLanguage: String,
    val numberOfEpisodes: Int,
//    val networks: List<NetworksItem>,
    val type: String,
    val backdropPath: String,
//    val genres: List<GenresItem>,
    val popularity: Double,
//    val productionCountries: List<ProductionCountriesItem>,
    val numberOfSeasons: Int,
    val voteCount: Int,
    val firstAirDate: String,
    val overview: String,
//    val seasons: List<SeasonsItem>,
    val languages: List<String>,
//    val createdBy: List<CreatedByItem>,
//    val lastEpisodeToAir: LastEpisodeToAir,
    val posterPath: String,
//    val originCountry: List<String>,
//    val spokenLanguages: List<SpokenLanguagesItem>,
//    val productionCompanies: List<ProductionCompaniesItem>,
    val originalName: String,
    val voteAverage: Double,
    val name: String,
    val tagline: String,
//    val episodeRunTime: List<Int>,
    val adult: Boolean,
//    val nextEpisodeToAir: NextEpisodeToAir,
    val inProduction: Boolean,
    val lastAirDate: String,
    val homepage: String,
    val status: String



)