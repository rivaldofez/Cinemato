package com.rivaldofez.core.datasource.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rivaldofez.core.datasource.remote.response.*
import com.rivaldofez.core.datasource.remote.response.subresponse.GenresItem
import com.rivaldofez.core.datasource.remote.response.subresponse.ProductionCompaniesItem
import com.rivaldofez.core.datasource.remote.response.subresponse.ProductionCountriesItem
import com.rivaldofez.core.datasource.remote.response.subresponse.SpokenLanguagesItem


@Entity(tableName = "tvshowDetail")
data class TvShowDetailLocalEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

//    @ColumnInfo(name = "original_language")
//    val originalLanguage: String,
//
//    @ColumnInfo(name = "number_of_episodes")
//    val numberOfEpisodes: Int,
//
//    @ColumnInfo(name = "networks")
//    val networks: List<NetworksItem>,
//
//    @ColumnInfo(name = "type")
//    val type: String,
//
//    @ColumnInfo(name = "backdrop_path")
//    val backdropPath: String,
//
//    @ColumnInfo(name = "genres")
//    val genres: List<GenresItem>,
//
//    @ColumnInfo(name = "popularity")
//    val popularity: Double,
//
//    @ColumnInfo(name = "production_countries")
//    val productionCountries: List<ProductionCountriesItem>,
//
//    @ColumnInfo(name = "number_of_seasons")
//    val numberOfSeasons: Int,
//
//    @ColumnInfo(name = "vote_count")
//    val voteCount: Int,
//
//    @ColumnInfo(name = "first_air_date")
//    val firstAirDate: String,
//
//    @ColumnInfo(name = "overview")
//    val overview: String,
//
//    @ColumnInfo(name = "seasons")
//    val seasons: List<SeasonsItem>,
//
//    @ColumnInfo(name = "languages")
//    val languages: List<String>,
//
//    @ColumnInfo(name = "created_by")
//    val createdBy: List<CreatedByItem>,
//
//    @ColumnInfo(name = "last_episode_to_air")
//    val lastEpisodeToAir: LastEpisodeToAir,
//
//    @ColumnInfo(name = "poster_path")
//    val posterPath: String,
//
//    @ColumnInfo(name = "origin_country")
//    val originCountry: List<String>,
//
//    @ColumnInfo(name = "spoken_languages")
//    val spokenLanguages: List<SpokenLanguagesItem>,
//
//    @ColumnInfo(name = "production_companies")
//    val productionCompanies: List<ProductionCompaniesItem>,
//
//    @ColumnInfo(name = "original_name")
//    val originalName: String,
//
//    @ColumnInfo(name = "vote_average")
//    val voteAverage: Double,
//
    @ColumnInfo(name = "name")
    val name: String,
//
//    @ColumnInfo(name = "tagline")
//    val tagline: String,
//
//    @ColumnInfo(name = "episode_run_time")
//    val episodeRunTime: List<Int>,
//
//    @ColumnInfo(name = "adult")
//    val adult: Boolean,
//
//    @ColumnInfo(name = "next_episode_to_air")
//    val nextEpisodeToAir: NextEpisodeToAir,
//
//    @ColumnInfo(name = "in_production")
//    val inProduction: Boolean,
//
//    @ColumnInfo(name = "last_air_date")
//    val lastAirDate: String,
//
//    @ColumnInfo(name = "homepage")
//    val homepage: String,
//
//    @ColumnInfo(name = "status")
//    val status: String


)