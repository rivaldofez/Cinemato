package com.rivaldofez.core.datasource.remote.response.subresponse

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesItem(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("iso_639_1")
    val iso6391: String,

    @field:SerializedName("english_name")
    val englishName: String
)