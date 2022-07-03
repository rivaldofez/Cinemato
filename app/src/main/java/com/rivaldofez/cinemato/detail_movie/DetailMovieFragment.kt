package com.rivaldofez.cinemato.detail_movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.FragmentDetailMovieBinding
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
            print(movieId)
        }
    }
}