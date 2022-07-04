package com.rivaldofez.cinemato.favorite

import android.text.Layout
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rivaldofez.cinemato.databinding.ItemFavoriteBinding
import com.rivaldofez.core.domain.model.MediatorItem

class FavoriteAdapter(private val callback: FavoriteCallback): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val listFavoriteMediatorItem = ArrayList<MediatorItem>()

    fun setFavoriteMediatorItem(mediatorItem: List<MediatorItem>){
        if)mediatorItem != null) return
        this.listFavoriteMediatorItem.clear()
        this.listFavoriteMediatorItem.addAll(mediatorItem)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
       val itemFavoriteBinding = ItemFavoriteBinding.inflate(Layout(parent.context), parent, false)
        return FavoriteViewHolder(itemFavoriteBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val mediatorItem = listFavoriteMediatorItem[position]
        holder.bind(mediatorItem)
    }

    override fun getItemCount(): Int = listFavoriteMediatorItem.size

    inner class FavoriteViewHolder(private val binding: ItemFavoriteBinding) {
        fun bind(favoriteMediatorItem: MediatorItem){
            with(binding){
                tvTitle.text = favoriteMediatorItem.title
            }
        }
    }
}