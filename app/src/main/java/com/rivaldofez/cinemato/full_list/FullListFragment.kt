package com.rivaldofez.cinemato.full_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rivaldofez.cinemato.databinding.FragmentFullListBinding
import com.rivaldofez.cinemato.movie.MovieCallback
import com.rivaldofez.cinemato.tvshow.TvShowCallback
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.TvShow
import com.rivaldofez.core.utils.MovieDataMapper
import com.rivaldofez.core.utils.TvShowDataMapper
import org.koin.android.viewmodel.ext.android.viewModel

class FullListFragment : Fragment(), MovieCallback, TvShowCallback {
    private var _binding: FragmentFullListBinding? = null
    private val binding get() = _binding!!
    private var totalPage = 1
    private var currentPage = 1
    private lateinit var gridLayoutManager: GridLayoutManager
    private val fullListViewModel: FullListViewModel by viewModel()
    private var action: String? = null
    private val rvAdapter = FullListAdapter(this, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFullListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        action = FullListFragmentArgs.fromBundle(arguments as Bundle).typeAction
//        swipeRefresh.setOn

        if(action != null){
            gridLayoutManager = GridLayoutManager(context, 2)
            with(binding.rvFullList){
                layoutManager = gridLayoutManager
                adapter = rvAdapter
            }
            infiniteScrollSetup(rvAdapter)
            selectCallObserver()
        }

        action?.let { Log.d("Teston", it) }

        (activity as AppCompatActivity?)!!.supportActionBar?.title = "Detail Movie"

    }

    override fun onMovieItemClick(movie: Movie) {
        val gotoDetailMovieFragment = FullListFragmentDirections.actionFullListFragmentToDetailMovie(
            movie.id.toString()
        )
        findNavController().navigate(gotoDetailMovieFragment)
    }

    override fun ontvShowItemClick(tvShow: TvShow) {
        val gotoDetailTvShowFragment = FullListFragmentDirections.actionFullListFragmentToDetailTvShowFragment(
            tvShow.id.toString()
        )
        findNavController().navigate(gotoDetailTvShowFragment)
    }

    private fun infiniteScrollSetup(adapter: FullListAdapter){
        binding.rvFullList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = gridLayoutManager.childCount
                val pastVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
                totalPage = adapter.itemCount
                if(currentPage < totalPage){
                    Log.d("Teston", "current < total" + currentPage.toString() + " " + totalPage.toString())
                    Log.d("Teston", "visible" +" pastvisible" + visibleItemCount.toString() +"  "+pastVisibleItem.toString())
                    if(visibleItemCount + pastVisibleItem >= totalPage){
                        Log.d("Teston", "call page ++")
                        currentPage++
                        selectCallObserver()
                    }
                }
                Log.d("Teston", "scroller " + currentPage.toString())

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun selectCallObserver(){
        if(action == ACTION_MOVIE_POPULAR){
            callObservePopularMovies()
        }else if(action == ACTION_MOVIE_TOPRATED){
            callObserveTopRatedMovies()
        }else if(action == ACTION_MOVIE_NOWPLAYING){
            callObserveNowPlayingMovies()
        }else if(action == ACTION_MOVIE_UPCOMING){
            callObserveUpcomingMovies()
        }else if(action == ACTION_TV_POPULAR){
            callObservePopularTvShow()
        }else if(action == ACTION_TV_TOPRATED){
            callObserveTopRatedTvShow()
        }else if(action == ACTION_TV_ONAIR){
            callObserveOnTheAirTvShow()
        }else if(action == ACTION_TV_AIRING){
            callObserveAiringTodayTvShow()
        }
    }

    private fun callObservePopularMovies(){
        fullListViewModel.getPopularMovies(currentPage.toString()).observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> {
                        val mediatorItem = movies.data?.let {
                            MovieDataMapper.mapListMovieToMediatorItem(
                                it
                            )
                        }
                        if (mediatorItem != null) {
                            rvAdapter.setMediatorItem(mediatorItem)
                            Log.d("Teston", "setAdapter")
                        }
                    }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveTopRatedMovies(){
        fullListViewModel.getTopRatedMovies(currentPage.toString()).observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { rvAdapter.setMediatorItem(mediatorItems = MovieDataMapper.mapListMovieToMediatorItem(
                        movies.data!!
                    )) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveUpcomingMovies(){
        fullListViewModel.getUpComingMovies(currentPage.toString()).observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { rvAdapter.setMediatorItem(mediatorItems = MovieDataMapper.mapListMovieToMediatorItem(
                        movies.data!!
                    )) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveNowPlayingMovies(){
        fullListViewModel.getNowPlayingMovies(currentPage.toString()).observe(viewLifecycleOwner, { movies ->
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { rvAdapter.setMediatorItem(mediatorItems = MovieDataMapper.mapListMovieToMediatorItem(
                        movies.data!!
                    )) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObservePopularTvShow(){
        fullListViewModel.getPopularTvShow(currentPage.toString()).observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { rvAdapter.setMediatorItem(mediatorItems = TvShowDataMapper.mapListTvShowToMediatorItem(
                        tvshows.data!!
                    )) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveTopRatedTvShow(){
        fullListViewModel.getTopRatedTvShow(currentPage.toString()).observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { rvAdapter.setMediatorItem(mediatorItems = TvShowDataMapper.mapListTvShowToMediatorItem(
                        tvshows.data!!
                    )) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveOnTheAirTvShow(){
        fullListViewModel.getOnTheAirTvShow(currentPage.toString()).observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { rvAdapter.setMediatorItem(mediatorItems = TvShowDataMapper.mapListTvShowToMediatorItem(
                        tvshows.data!!
                    )) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
    }

    private fun callObserveAiringTodayTvShow(){
        fullListViewModel.getAiringTodayTvShow(currentPage.toString()).observe(viewLifecycleOwner, {tvshows ->
            if(tvshows != null){
                when(tvshows) {
                    is Resource.Success -> tvshows.data?.let { rvAdapter.setMediatorItem(mediatorItems = TvShowDataMapper.mapListTvShowToMediatorItem(
                        tvshows.data!!
                    )) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })
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
}