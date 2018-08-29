package com.muzadev.dicodingkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.model.Club
import kotlinx.android.synthetic.main.item_club.view.*

/**
 * Created by zulfakar on 29/08/18.
 * For educational purposes
 */
class ClubAdapter(val context: Context, val clubs: List<Club>, val listener: (Club) -> Unit) : RecyclerView.Adapter<ClubAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_club, parent, false))
    }

    override fun getItemCount(): Int {
        return clubs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(clubs[position], listener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(club: Club, listener: (Club) -> Unit) {
            itemView.tvClubName.text = club.name
            Glide.with(itemView.context)
                    .load(club.emblem)
                    .into(itemView.imgClubEmblem)

            itemView.setOnClickListener {
                listener(club)
            }
        }
    }

}