package com.rivaldofez.core.utils

import com.rivaldofez.core.datasource.remote.response.subresponse.GenresItem
import com.rivaldofez.core.datasource.remote.response.subresponse.SpokenLanguagesItem

object MapperAtribute {
    fun mapListGenreToString(input: List<GenresItem>) : String =
        input.joinToString { it.name }

    fun mapListSpokenLanguageToString(input: List<SpokenLanguagesItem>) : String =
        input.joinToString { it.englishName }
}