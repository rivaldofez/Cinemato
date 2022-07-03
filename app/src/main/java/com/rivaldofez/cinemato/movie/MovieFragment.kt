
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

            with(binding.rvPopularMovie){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                adapter = popularAdapter
            }
        }
    }

    private fun callObservePopularMovies(){
        movieViewModel.getTopRatedMovies("1").observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { popularAdapter.setMovie(movies = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

}