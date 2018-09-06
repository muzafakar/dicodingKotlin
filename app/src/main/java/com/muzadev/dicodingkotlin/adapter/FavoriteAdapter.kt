package com.muzadev.dicodingkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.model.Favorite
import kotlinx.android.synthetic.main.item_club.view.*

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class FavoriteAdapter(private val context: Context, private val favorite: List<Favorite>, private val listener: (Favorite) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.item_club,
                    parent,
                    false)
    )


    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
            itemView.tvTeamName.text = favorite.teamName
            Glide.with(context).load(favorite.teamBadge).into(itemView.imgTeam)
            itemView.setOnClickListener {
                listener(favorite)
            }
        }
    }
}
