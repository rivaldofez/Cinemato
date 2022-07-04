package com.rivaldofez.cinemato.favorite

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.cinemato.BuildConfig
import com.rivaldofez.cinemato.R
import com.rivaldofez.cinemato.databinding.ItemFavoriteBinding
import com.rivaldofez.core.domain.model.MediatorItem
import com.rivaldofez.core.utils.ViewHelper

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavouriteViewHolder>() {


    private val listMediatorItem = ArrayList<MediatorItem>()

    fun setMediatorItem(mediatorItem: List<MediatorItem>){
        if(mediatorItem == null) return
        this.listMediatorItem.clear()
        this.listMediatorItem.addAll(mediatorItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): FavouriteViewHolder {
        val itemFavoriteBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteViewHolder(itemFavoriteBinding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavouriteViewHolder, position: Int) {
        val mediatorItem = listMediatorItem[position]
        holder.bind(mediatorItem)
    }

    override fun getItemCount(): Int = listMediatorItem.size

    class FavouriteViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(mediatorItem: MediatorItem){
            with(binding){
                tvTitle.text = mediatorItem.title
                tvPopularity.text = String.format("%.0f",((mediatorItem.voteAverage/10.0)*100))
                tvItemDate.text = ViewHelper.formatDate(mediatorItem.releaseDate)
                Glide.with(imgPoster.context).load(BuildConfig.API_PATH_IMAGE + mediatorItem.posterPath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_favorite)
                            .error(R.drawable.ic_favorite)).into(imgPoster)
                ratingItem.rating = (0.5F * mediatorItem.voteAverage).toFloat()
            }
        }
    }
}