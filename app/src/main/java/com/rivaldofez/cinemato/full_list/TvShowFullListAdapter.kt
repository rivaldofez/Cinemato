package com.rivaldofez.cinemato.full_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rivaldofez.cinemato.databinding.ItemCinemaBinding
import com.rivaldofez.cinemato.tvshow.TvShowCallback
import com.rivaldofez.core.domain.model.Movie
import com.rivaldofez.core.domain.model.TvShow

class TvShowFullListAdapter(private val callback: TvShowCallback): RecyclerView.Adapter<TvShowFullListAdapter.TvShowFullListViewHolder>() {
    private val listTvShow = ArrayList<TvShow>()

    fun setTvShow(tvshows: List<TvShow>){
        if(tvshows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvshows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowFullListViewHolder {
        val itemTvShowBinding = ItemCinemaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowFullListViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowFullListViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowFullListViewHolder(private val binding: ItemCinemaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow){
            with(binding){
                cvItemCinema.setOnClickListener{callback.ontvShowItemClick(tvShow)}
                tvPopularity.text = String.format("%.0f",((tvShow.voteAverage/10.0)*100))
                tvTitle.text = tvShow.name
                com.bumptech.glide.Glide.with(itemView.context).load(com.rivaldofez.cinemato.BuildConfig.API_PATH_IMAGE + tvShow.posterPath)
                    .apply(
                        com.bumptech.glide.request.RequestOptions.placeholderOf(com.rivaldofez.cinemato.R.drawable.ic_favorite)
                            .error(com.rivaldofez.cinemato.R.drawable.ic_favorite)).into(imgPoster)
                ratingCinema.rating = (0.5F * tvShow.voteAverage).toFloat()
            }
        }
    }
}