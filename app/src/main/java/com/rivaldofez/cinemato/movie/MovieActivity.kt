package com.rivaldofez.cinemato.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rivaldofez.cinemato.databinding.ActivityMovieBinding
import com.rivaldofez.core.datasource.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private val movieAdapter = MovieAdapter()
    private val movieViewModel: MovieViewModel by viewModel()
    private var page = 1
    private var totalPage = 10
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this)
        binding.rvMovie.layoutManager = layoutManager
        binding.rvMovie.adapter = movieAdapter
        binding.rvMovie.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount: Int = layoutManager.childCount
                val pastVisibleItem: Int = layoutManager.findFirstVisibleItemPosition()
                val total = movieAdapter.itemCount
                if(page < totalPage){
                    if(visibleItemCount + pastVisibleItem >= total){
                        page++
                        movieViewModel.getPopularMovies(page.toString()).observe(this@MovieActivity, {movies ->
                            Log.d("Teston", "hellooo"+movies.data.toString())
                            if(movies != null){
                                when(movies){
                                    is Resource.Success -> movies.data?.let { movieAdapter.setMovie(movies = it) }
                                    is Resource.Loading -> Log.d("Teston", "loading")
                                    is Resource.Error -> Log.d("Teston", "error")
                                }
                            }
                        })

                    }
                }


                super.onScrolled(recyclerView, dx, dy)
            }
        })


        movieViewModel.getPopularMovies(page.toString()).observe(this, {movies ->
            Log.d("Teston", "hellooo"+movies.data.toString())
            if(movies != null){
                when(movies){
                    is Resource.Success -> movies.data?.let { movieAdapter.setMovie(movies = it) }
                    is Resource.Loading -> Log.d("Teston", "loading")
                    is Resource.Error -> Log.d("Teston", "error")
                }
            }
        })

        movieViewModel.hello()
    }


}