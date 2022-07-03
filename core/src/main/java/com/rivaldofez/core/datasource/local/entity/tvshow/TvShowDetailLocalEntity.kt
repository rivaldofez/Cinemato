package com.rivaldofez.core.datasource.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rivaldofez.core.datasource.remote.response.*
import com.rivaldofez.core.datasource.remote.response.subresponse.GenresItem
import com.rivaldofez.core.datasource.remote.response.subresponse.ProductionCompaniesItem
import com.rivaldofez.core.datasource.remote.response.subresponse.ProductionCountriesItem
import com.rivaldofez.core.datasource.remote.response.subresponse.SpokenLanguagesItem


@Entity(tableName = "tvshowdetail")
data class TvShowDetailLocalEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "original_name")
    val originalName: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "number_of_episodes")
    val numberOfEpisodes: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "popularity")
    val popularity: Double,

    @ColumnInfo(name = "number_of_seasons")
    val numberOfSeasons: Int,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "tagline")
    val tagline: String,

    @ColumnInfo(name = "adult")
    val adult: Boolean,

    @ColumnInfo(name = "in_production")
    val inProduction: Boolean,

    @ColumnInfo(name = "last_air_date")
    val lastAirDate: String,

    @ColumnInfo(name = "homepage")
    val homepage: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "genres")
    val genres: String,

    @ColumnInfo(name = "spoken_languages")
    val spokenLanguages: String,

)