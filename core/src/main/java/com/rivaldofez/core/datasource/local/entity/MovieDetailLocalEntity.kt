package com.rivaldofez.core.datasource.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviedetail")
data class MovieDetailLocalEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

//    @ColumnInfo(name = "original_language")
//    val originalLanguage: String,
//
//    @ColumnInfo(name = "imdb_id")
//    val imdbId: String,
//
//    @ColumnInfo(name = "video")
//    val video: Boolean,
//
    @ColumnInfo(name = "title")
    val title: String,
//
//    @ColumnInfo(name = "backdrop_path")
//    val backdropPath: String,
//
//    @ColumnInfo(name = "revenue")
//    val revenue: Int,
//
////    @ColumnInfo(name = "genres")
////    val genres: List<GenresItem>,
//
//    @ColumnInfo(name = "popularity")
//    val popularity: Double,
//
////    @ColumnInfo(name = "production_countries")
////    val productionCountries: List<ProductionCountriesItem>,
//
//    @ColumnInfo(name = "vote_count")
//    val voteCount: Int,
//
//    @ColumnInfo(name = "budget")
//    val budget: Int,
//
//    @ColumnInfo(name = "overview")
//    val overview: String,
//
//    @ColumnInfo(name = "original_title")
//    val originalTitle: String,
//
//    @ColumnInfo(name = "runtime")
//    val runtime: Int,
//
//    @ColumnInfo(name = "poster_path")
//    val posterPath: String,
//
////    @ColumnInfo(name = "spoken_languages")
////    val spokenLanguages: List<SpokenLanguagesItem>,
//
////    @ColumnInfo(name = "production_companies")
////    val productionCompanies: List<ProductionCompaniesItem>,
//
//    @ColumnInfo(name = "release_date")
//    val releaseDate: String,
//
//    @ColumnInfo(name = "vote_average")
//    val voteAverage: Double,
//
//    @ColumnInfo(name = "belongs_to_collection")
//    val belongsToCollection: String,
//
//    @ColumnInfo(name = "tagline")
//    val tagline: String,
//
//    @ColumnInfo(name = "adult")
//    val adult: Boolean,
//
//    @ColumnInfo(name = "homepage")
//    val homepage: String,
//
//    @ColumnInfo(name = "status")
//    val status: String




)
