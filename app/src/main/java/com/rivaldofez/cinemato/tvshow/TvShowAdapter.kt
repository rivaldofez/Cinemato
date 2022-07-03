package com.rivaldofez.cinemato.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.cinemato.BuildConfig
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.ItemCinemaBinding
import com.rivaldofez.cinemato.databinding.ItemCinemaHorizontalBinding
import com.rivaldofez.core.domain.model.TvShow

class TvShowAdapter(private val callback: TvShowCallback): RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
    private val listTvShow = ArrayList<TvShow>()

    fun setTvShow(tvshows: List<TvShow>){
        if(tvshows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvshows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemTvShowBinding = ItemCinemaHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvshow = listTvShow[position]
        holder.bind(tvshow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemCinemaHorizontalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow){
            with(binding){
                cvItemCinema.setOnClickListener { callback.ontvShowItemClick(tvShow) }

                tvTitle.text = tvShow.name
                tvPopularity.text = String.format("%.0f",((tvShow.voteAverage/10.0)*100))
                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + tvShow.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_favorite)
                            .error(R.drawable.ic_favorite)).into(imgPoster)
                ratingCinema.rating = (0.5F * tvShow.voteAverage).toFloat()
            }
        }
    }
}