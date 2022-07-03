package com.rivaldofez.cinemato.tvshow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.FragmentTvshowBinding
import com.rivaldofez.core.datasource.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class TVSeriesFragment : Fragment() {
    private var _binding: FragmentTvshowBinding? = null
    private val binding get() = _binding!!
    private val tvShowViewModel: TvShowViewModel by viewModel()
    private val popularAdapter = TvShowAdapter()
    private val topRatedAdapter = TvShowAdapter()
    private val onTheAirAdapter = TvShowAdapter()
    private val airingTodayAdapter = TvShowAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            callObserveAiringTodayTvShow()
            callObserveOnTheAirTvShow()
            callObservePopularTvShow()
            callObserveTopRatedTvShow()

            with(binding.rvPopularTvshow){
                val layoutManagerPopular = LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerPopular
                adapter = popularAdapter
            }

            with(binding.rvTopratedTvshow){
                val layoutManagerPopular = LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerPopular
                adapter = topRatedAdapter
            }

            with(binding.rvOnTheAirTvshow){
                val layoutManagerPopular = LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerPopular
                adapter = onTheAirAdapter
            }

            with(binding.rvAiringTodayTvshow){
                val layoutManagerPopular = LinearLayoutManager(context, androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerPopular
                adapter = airingTodayAdapter
            }
        }
    }

    private fun callObservePopularTvShow(){
        tvShowViewModel.getPopularTvShow("1").observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { popularAdapter.setTvShow(tvshows = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveTopRatedTvShow(){
        tvShowViewModel.getTopRatedTvShow("1").observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { topRatedAdapter.setTvShow(tvshows = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveOnTheAirTvShow(){
        tvShowViewModel.getOnTheAirTvShow("1").observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { onTheAirAdapter.setTvShow(tvshows = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveAiringTodayTvShow(){
        tvShowViewModel.getAiringTodayTvShow("1").observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { airingTodayAdapter.setTvShow(tvshows = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

}