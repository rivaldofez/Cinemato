package com.rivaldofez.cinemato.detail_tvshow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.cinemato.BuildConfig
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.FragmentDetailTvshowBinding
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.TvShowDetail
import com.rivaldofez.core.utils.ViewHelper
import org.koin.android.viewmodel.ext.android.viewModel


class DetailTvShowFragment : Fragment() {
    private var _binding: FragmentDetailTvshowBinding? = null
    private val binding get() = _binding!!
    private val detailTvShowViewModel: DetailTvShowViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTvshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvShowId = DetailTvShowFragmentArgs.fromBundle(arguments as Bundle).tvShowId

        if(tvShowId != null) {
            detailTvShowViewModel.getDetailTvShow(tvShowId).observe(viewLifecycleOwner, { detailTvShow ->
                when (detailTvShow) {
                    is Resource.Success -> {
                        detailTvShow.data?.let { setContentView(it) }
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

    private fun setContentView(detailTvShow: TvShowDetail) {
        with(binding){
            cgGenres.removeAllViews()
            detailTvShow.genres.split(",").map{
                val itemGenre = ViewHelper.generateChipItem(it.trim(), requireContext())
                if(itemGenre != null){
                    cgGenres.addView(itemGenre)
                }
            }
            cgLanguage.removeAllViews()
            detailTvShow.spokenLanguages.split(",").map {
                val itemSpokenLanguage = ViewHelper.generateChipItem(it.trim(), requireContext())
                if(itemSpokenLanguage != null){
                    cgLanguage.addView(itemSpokenLanguage)
                }
            }

            Glide.with(requireContext()).load(BuildConfig.API_PATH_IMAGE + detailTvShow.posterPath).apply(
                RequestOptions.placeholderOf(R.drawable.ic_favorite).error(R.drawable.ic_feed)).into(imgPoster)
            Glide.with(requireContext()).load(BuildConfig.API_PATH_IMAGE + detailTvShow.posterPath).apply(
                RequestOptions.placeholderOf(R.drawable.ic_favorite).error(R.drawable.ic_feed)).into(imgBackdrop)

            tvDate.text = ViewHelper.formatDate(detailTvShow.firstAirDate)
            tvHomepage.text = detailTvShow.homepage
            tvOriginal.text = detailTvShow.originalLanguage
            tvTitle.text = detailTvShow.name
            tvSynopsis.text = detailTvShow.overview
            tvStatus.text = detailTvShow.status
            chartPopularity.setProgress((detailTvShow.voteAverage.toFloat() * 10F), true)
            tvEpisode.text = detailTvShow.numberOfEpisodes.toString()
            tvSeason.text = detailTvShow.numberOfSeasons.toString()
            tvTagline.text = if (detailTvShow.tagline == "") "No tagline Defined" else detailTvShow.tagline
        }
    }
}