package com.rivaldofez.cinemato.detail_movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.rivaldofez.cinemato.BuildConfig
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.FragmentDetailMovieBinding
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.MovieDetail
import com.rivaldofez.core.utils.ViewHelper
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!
    private val detailMovieViewModel: DetailMovieViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = DetailMovieFragmentArgs.fromBundle(arguments as Bundle).movieId

        if(movieId != null) {
            detailMovieViewModel.getDetailMovie(movieId).observe(viewLifecycleOwner, { detailMovie ->
                when (detailMovie) {
                    is Resource.Success -> {
                        detailMovie.data?.let { setContentView(it) }
                    }

                    is Resource.Error -> {
                        Log.d("Teston", "Error")
                    }

                    is Resource.Loading -> {
                        Log.d("Teston", "Loading")
                    }
                }
            })
        }
    }

    private fun setContentView(detailMovie: MovieDetail){
        with(binding){
            cgGenres.removeAllViews()
            detailMovie.genres.split(",").map{
                val itemGenre = ViewHelper.generateChipItem(it.trim(), requireContext())
                if(itemGenre != null){
                    cgGenres.addView(itemGenre)
                }
            }
            cgLanguage.removeAllViews()
            detailMovie.spokenLanguages.split(",").map {
                val itemSpokenLanguage = ViewHelper.generateChipItem(it.trim(), requireContext())
                if(itemSpokenLanguage != null){
                    cgLanguage.addView(itemSpokenLanguage)
                }
            }

            Glide.with(requireContext()).load(BuildConfig.API_PATH_IMAGE + detailMovie.posterPath).apply(
                RequestOptions.placeholderOf(R.drawable.ic_favorite).error(R.drawable.ic_feed)).into(imgPoster)
            Glide.with(requireContext()).load(BuildConfig.API_PATH_IMAGE + detailMovie.posterPath).apply(
                RequestOptions.placeholderOf(R.drawable.ic_favorite).error(R.drawable.ic_feed)).into(imgBackdrop)

            tvDate.text = ViewHelper.formatDate(detailMovie.releaseDate)
            tvBudget.text = ViewHelper.formatCurrency(detailMovie.budget)
            tvDuration.text = ViewHelper.formatRuntime(detailMovie.runtime)
            tvHomepage.text = detailMovie.homepage
            tvOriginal.text = detailMovie.originalLanguage
            tvRevenue.text = ViewHelper.formatCurrency(detailMovie.revenue)
            tvTitle.text = detailMovie.title
            tvSynopsis.text = detailMovie.overview
            tvStatus.text = detailMovie.status
            chartPopularity.setProgress((detailMovie.voteAverage.toFloat() * 10F), true)

            btnFavorite.apply {
                setStateFavoriteIcon(detailMovie.isFavorite)
                setOnClickListener {
                    detailMovieViewModel.setFavoriteMovie(detailMovie, !detailMovie.isFavorite)
                    setStateFavoriteIcon(detailMovie.isFavorite)
                    showSnackBarFavorite(!detailMovie.isFavorite)
                }
            }
        }
    }

    private fun setStateFavoriteIcon(isFavorite: Boolean){
        if(isFavorite)
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite)
            )
        else
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_unfilled)
            )
    }

    private fun showSnackBarFavorite(isFavorite: Boolean){
        if(isFavorite){
            val snackbar = Snackbar.make(binding.root, "Added to Favorite List", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }else{
            val snackbar = Snackbar.make(binding.root, "Removed from Favorite List", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }
}