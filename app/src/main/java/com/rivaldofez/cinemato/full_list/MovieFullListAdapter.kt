package com.rivaldofez.cinemato.full_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.cinemato.BuildConfig
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.ItemCinemaBinding
import com.rivaldofez.cinemato.movie.MovieAdapter
import com.rivaldofez.cinemato.movie.MovieCallback
import com.rivaldofez.core.domain.model.Movie

class MovieFullListAdapter(private val callback: MovieCallback): RecyclerView.Adapter<MovieFullListAdapter.MovieFullListViewHolder>() {

    private val listMovie = ArrayList<Movie>()

    fun setMovie(movies: List<Movie>){
        if(movies == null ) return
        this.listMovie.clear()
        this.listMovie.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieFullListAdapter.MovieFullListViewHolder {
        val itemMovieBinding = ItemCinemaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFullListViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieFullListAdapter.MovieFullListViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieFullListViewHolder(private val binding: ItemCinemaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie){
            with(binding){
                cvItemCinema.setOnClickListener{callback.onMovieItemClick(movie)}
                tvPopularity.text = String.format("%.0f",((movie.voteAverage/10.0)*100))
                tvTitle.text = movie.title
                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + movie.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_favorite)
                            .error(R.drawable.ic_favorite)).into(imgPoster)
                ratingCinema.rating = (0.5F * movie.voteAverage).toFloat()
            }
        }
    }
}