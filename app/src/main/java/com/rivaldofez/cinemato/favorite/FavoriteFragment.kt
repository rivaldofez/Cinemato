package com.rivaldofez.cinemato.favorite

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivaldofez.cinemato.databinding.FragmentFavoriteBinding
import com.rivaldofez.cinemato.full_list.FullListFragmentDirections
import com.rivaldofez.cinemato.movie.MovieCallback
import com.rivaldofez.cinemato.tvshow.TvShowCallback
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.utils.MovieDataMapper
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteFragment() : Fragment() , MovieCallback, TvShowCallback {

    private var _binding : FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel : FavoriteViewModel by viewModel()
    private val favoriteAdapter = FavoriteAdapter(this,this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            with(binding.rvFavoriteGame){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = favoriteAdapter
                Log.d("Teston", "setup rv")
            }
            callObserveMovieFavorite()
        }
    }


    private fun callObserveMovieFavorite(){
        favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { favoriteMovies ->
            if(favoriteMovies != null && favoriteMovies.isNotEmpty()){
                favoriteAdapter.setMediatorItem(MovieDataMapper.mapListDomainDetailMovieToMediatorItem(favoriteMovies))
                Log.d("Teston", MovieDataMapper.mapListDomainDetailMovieToMediatorItem(favoriteMovies).toString())

            }else{
                Log.d("Teston", "Hello")
            }
        })
    }

    override fun onMovieItemClick(movie: Movie) {
        val gotoDetailMovieFragment = FavoriteFragmentDirections.actionFavoriteFragmentToDetailMovie(
            movie.id.toString()
        )
        findNavController().navigate(gotoDetailMovieFragment)
    }

    override fun ontvShowItemClick(tvShow: TvShow) {
        val gotoDetailTvShowFragment = FavoriteFragmentDirections.actionFavoriteFragmentToDetailTvShowFragment(
            tvShow.id.toString()
        )
        findNavController().navigate(gotoDetailTvShowFragment)
    }
}