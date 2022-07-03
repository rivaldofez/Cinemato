
package com.rivaldofez.cinemato.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.FragmentMovieBinding
import com.rivaldofez.core.datasource.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModel()
    private val popularAdapter = MovieAdapter()
    private val topRatedAdapter = MovieAdapter()
    private val upcomingAdapter = MovieAdapter()
    private val nowPlayingAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            callObservePopularMovies()
            callObserveTopRatedMovies()
            callObserveUpcomingMovies()
            callObserveNowPlayingMovies()

            with(binding.rvPopularMovie){
                val layoutManagerPopular = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerPopular
                adapter = popularAdapter
            }

            with(binding.rvTopratedMovie){
                val layoutManagerTopRated = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerTopRated
                adapter = topRatedAdapter
            }

            with(binding.rvUpcomingMovie){
                val layoutManagerUpComing = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerUpComing
                adapter = upcomingAdapter
            }

            with(binding.rvNowPlayingMovie){
                val layoutManagerNowPlaying = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutManagerNowPlaying
                adapter = nowPlayingAdapter
            }
        }
    }

    private fun callObservePopularMovies(){
        movieViewModel.getPopularMovies("1").observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { popularAdapter.setMovie(movies = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveTopRatedMovies(){
        movieViewModel.getTopRatedMovies("1").observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { topRatedAdapter.setMovie(movies = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveUpcomingMovies(){
        movieViewModel.getUpComingMovies("1").observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { upcomingAdapter.setMovie(movies = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveNowPlayingMovies(){
        movieViewModel.getNowPlayingMovies("1").observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { nowPlayingAdapter.setMovie(movies = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

}