package com.rivaldofez.core.datasource.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tvshowlist")
data class TvShowItemLocalEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

//    @ColumnInfo(name = "first_air_date")
//    val firstAirDate: String,
//
//    @ColumnInfo(name = "overview")
//    val overview: String,
//
//    @ColumnInfo(name = "original_language")
//    val originalLanguage: String,

//    @ColumnInfo(name = "genre_ids")
//    val genreIds: List<Int>,

//    @ColumnInfo(name = "poster_path")
//    val posterPath: String,
//
//    @ColumnInfo(name = "origin_country")
//    val originCountry: List<String>,
//
//    @ColumnInfo(name = "backdrop_path")
//    val backdropPath: String,
//
//    @ColumnInfo(name = "original_name")
//    val originalName: String,
//
//    @ColumnInfo(name = "popularity")
//    val popularity: Double,
//
//    @ColumnInfo(name = "vote_average")
//    val voteAverage: Double,

    @ColumnInfo(name = "name")
    val name: String,

//    @ColumnInfo(name = "vote_count")
//    val voteCount: Int

)





