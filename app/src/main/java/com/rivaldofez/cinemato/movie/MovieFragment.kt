
package com.rivaldofez.cinemato.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.FragmentMovieBinding
import com.rivaldofez.cinemato.full_list.FullListFragment
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import org.koin.android.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(), MovieCallback {
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModel()
    private val popularAdapter = MovieAdapter(this)
    private val topRatedAdapter = MovieAdapter(this)
    private val upcomingAdapter = MovieAdapter(this)
    private val nowPlayingAdapter = MovieAdapter(this)

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

    override fun onMovieItemClick(movie: Movie) {
        val gotoDetailMovieFragment = MovieFragmentDirections.actionMovieFragmentToDetailMovie(
            movie.id.toString()
        )
        findNavController().navigate(gotoDetailMovieFragment)
    }

    private fun setClickViewAll(){
        binding.btnViewallPopular.setOnClickListener {
            actionToFullList(FullListFragment.ACTION_MOVIE_POPULAR)
        }

        binding.btnViewallToprated.setOnClickListener {
            actionToFullList(FullListFragment.ACTION_MOVIE_TOPRATED)
        }

        binding.btnViewallNowplaying.setOnClickListener {
            actionToFullList(FullListFragment.ACTION_MOVIE_NOWPLAYING)
        }

        binding.btnViewallUpcoming.setOnClickListener {
            actionToFullList(FullListFragment.ACTION_MOVIE_UPCOMING)
        }
    }

    private fun actionToFullList(action: String){
        val gotoFullListFragment = MovieFragmentDirections.actionMovieFragmentToFullListFragment(
            action
        )

        findNavController().navigate(gotoFullListFragment)
    }

}