package com.rivaldofez.cinemato.full_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rivaldofez.cinemato.R

class FullListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val ACTION_MOVIE_POPULAR = "action_movie_popular"
        const val ACTION_MOVIE_TOPRATED = "action_movie_toprated"
        const val ACTION_MOVIE_UPCOMING = "action_movie_upcoming"
        const val ACTION_MOVIE_NOWPLAYING = "action_movie_nowplaying"
        const val ACTION_TV_POPULAR = "action_tv_popular"
        const val ACTION_TV_TOPRATED = "action_tv_toprated"
        const val ACTION_TV_ONAIR = "action_tv_onair"
        const val ACTION_TV_AIRING = "action_tv_airing"
    }

}