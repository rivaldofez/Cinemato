package com.rivaldofez.core.datasource.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ontheairtvshow")
data class OnTheAirTvShowLocalEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,
)