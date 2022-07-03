package com.rivaldofez.cinemato.tvshow

import com.rivaldofez.core.domain.model.TvShow

interface TvShowCallback {
    fun ontvShowItemClick(tvShow: TvShow)
}