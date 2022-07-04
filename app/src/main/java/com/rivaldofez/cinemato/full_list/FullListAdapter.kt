package com.rivaldofez.cinemato.full_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.cinemato.BuildConfig
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.ItemCinemaBinding
import com.rivaldofez.cinemato.movie.MovieCallback
import com.rivaldofez.cinemato.tvshow.TvShowCallback
import com.rivaldofez.core.domain.model.MediatorItem
import com.rivaldofez.core.utils.MovieDataMapper
import com.rivaldofez.core.utils.TvShowDataMapper

class FullListAdapter(private val mCallback: MovieCallback, private val tCallback: TvShowCallback): RecyclerView.Adapter<FullListAdapter.MovieFullListViewHolder>() {

    private val listMediatorItem = ArrayList<MediatorItem>()

    fun setMediatorItem(mediatorItems: List<MediatorItem>){
        if(mediatorItems == null ) return
        this.listMediatorItem.clear()
        this.listMediatorItem.addAll(mediatorItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FullListAdapter.MovieFullListViewHolder {
        val itemMovieBinding = ItemCinemaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieFullListViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: FullListAdapter.MovieFullListViewHolder, position: Int) {
        val mediatorItem = listMediatorItem[position]
        holder.bind(mediatorItem)
    }

    override fun getItemCount(): Int = listMediatorItem.size

    inner class MovieFullListViewHolder(private val binding: ItemCinemaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(mediatorItem: MediatorItem){
            with(binding){
                if(mediatorItem.type == "movie"){
                    cvItemCinema.setOnClickListener{mCallback.onMovieItemClick(MovieDataMapper.mapMediatorItemToMovie(mediatorItem))}
                }else{
                    cvItemCinema.setOnClickListener{tCallback.ontvShowItemClick(TvShowDataMapper.mapMediatorItemToTvShow(mediatorItem))}
                }

                tvPopularity.text = String.format("%.0f",((mediatorItem.voteAverage/10.0)*100))
                tvTitle.text = mediatorItem.title
                Glide.with(itemView.context).load(BuildConfig.API_PATH_IMAGE + mediatorItem.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_favorite)
                            .error(R.drawable.ic_favorite)).into(imgPoster)
                ratingCinema.rating = (0.5F * mediatorItem.voteAverage).toFloat()
            }
        }
    }
}