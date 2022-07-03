package com.rivaldofez.cinemato.detail_movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            tvDate.text = ViewHelper.formatDate(detailMovie.releaseDate)
            tvBudget.text = ViewHelper.formatCurrency(detailMovie.budget)
            tvDuration.text = ViewHelper.formatRuntime(detailMovie.runtime)
            tvHomepage.text = detailMovie.homepage
            tvOriginal.text = detailMovie.originalLanguage
            tvRevenue.text = ViewHelper.formatCurrency(detailMovie.revenue)
            tvTitle.text = detailMovie.title
            tvSynopsis.text = detailMovie.overview
            tvStatus.text = detailMovie.status
            chartPopularity.setProgress((detailMovie.voteAverage.toFloat() * 10F) * 100, true)

        }
    }
}