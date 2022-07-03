package com.rivaldofez.cinemato.full_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rivaldofez.cinemato.databinding.FragmentFullListBinding
import com.rivaldofez.cinemato.movie.MovieCallback
import com.rivaldofez.cinemato.movie.MovieViewModel
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import org.koin.android.viewmodel.ext.android.viewModel

class FullListFragment : Fragment(), MovieCallback {
    private var _binding: FragmentFullListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val action = FullListFragmentArgs.fromBundle(arguments as Bundle).typeAction

        if(action != null){
            if(action == ACTION_MOVIE_POPULAR){
                val movieViewModel: MovieViewModel by viewModel()
                val movieFullistAdapter = MovieFullListAdapter(this)
                with(binding.rvFullList){
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = movieFullistAdapter
                }

                movieViewModel.getPopularMovies(page = "1").observe(viewLifecycleOwner, {movies ->
                    when(movies){
                        is Resource.Success -> movies.data?.let { movieFullistAdapter.setMovie(movies = it) }
                        is Resource.Loading -> Log.d("Teston", "loading")
                        is Resource.Error -> Log.d("Teston", "error")
                    }
                })
            }else if(action == ACTION_MOVIE_TOPRATED){
                val movieViewModel: MovieViewModel by viewModel()
                val movieFullistAdapter = MovieFullListAdapter(this)
                with(binding.rvFullList){
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = movieFullistAdapter
                }

                movieViewModel.getTopRatedMovies(page = "1").observe(viewLifecycleOwner, {movies ->
                    when(movies){
                        is Resource.Success -> movies.data?.let { movieFullistAdapter.setMovie(movies = it) }
                        is Resource.Loading -> Log.d("Teston", "loading")
                        is Resource.Error -> Log.d("Teston", "error")
                    }
                })
            }else if(action == ACTION_MOVIE_NOWPLAYING){
                val movieViewModel: MovieViewModel by viewModel()
                val movieFullistAdapter = MovieFullListAdapter(this)
                with(binding.rvFullList){
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = movieFullistAdapter
                }

                movieViewModel.getNowPlayingMovies(page = "1").observe(viewLifecycleOwner, {movies ->
                    when(movies){
                        is Resource.Success -> movies.data?.let { movieFullistAdapter.setMovie(movies = it) }
                        is Resource.Loading -> Log.d("Teston", "loading")
                        is Resource.Error -> Log.d("Teston", "error")
                    }
                })
            }else if(action == ACTION_MOVIE_UPCOMING){
                val movieViewModel: MovieViewModel by viewModel()
                val movieFullistAdapter = MovieFullListAdapter(this)
                with(binding.rvFullList){
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = movieFullistAdapter
                }

                movieViewModel.getPopularMovies(page = "1").observe(viewLifecycleOwner, {movies ->
                    when(movies){
                        is Resource.Success -> movies.data?.let { movieFullistAdapter.setMovie(movies = it) }
                        is Resource.Loading -> Log.d("Teston", "loading")
                        is Resource.Error -> Log.d("Teston", "error")
                    }
                })
            }
        }
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

    override fun onMovieItemClick(movie: Movie) {
        val gotoDetailMovieFragment = FullListFragmentDirections.actionFullListFragmentToDetailMovie(
            movie.id.toString()
        )
        findNavController().navigate(gotoDetailMovieFragment)
    }
}