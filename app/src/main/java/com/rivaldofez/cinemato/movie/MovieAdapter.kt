package com.rivaldofez.cinemato.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.cinemato.BuildConfig
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.ItemCinemaHorizontalBinding
import com.rivaldofez.cinemato.databinding.ItemMovieBinding
import com.rivaldofez.core.datasource.Resource
import com.rivaldofez.core.domain.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private val listMovie = ArrayList<Movie>()

    fun setMovie(movies: List<Movie>){
        if(movies == null ) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding = ItemCinemaHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemCinemaHorizontalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
            with(binding) {
                tvTitle.text = movie.title
                tvPopularity.text = String.format("%.0f",((movie.voteAverage/10.0)*100))
                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_favorite)
                        .error(R.drawable.ic_favorite)).into(imgPoster)
                ratingCinema.rating = (0.5F * movie.voteAverage).toFloat()
            }
        }
    }
}