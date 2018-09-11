package com.muzadev.dicodingkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.model.EventFavorite
import kotlinx.android.synthetic.main.item_match.view.*

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class FavoriteEventAdapter(private val context: Context, private val eventFavorite: List<EventFavorite>, private val listener: (EventFavorite) -> Unit) : RecyclerView.Adapter<FavoriteEventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.item_match,
                    parent,
                    false)
    )


    override fun getItemCount(): Int = eventFavorite.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(eventFavorite[position], listener)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(eventFavorite: EventFavorite, listener: (EventFavorite) -> Unit) {

            itemView.matchDate.text = eventFavorite.dateEvent
            itemView.homeTeam.text = eventFavorite.strHomeTeam
            itemView.scoreHome.text = eventFavorite.intHomeScore
            itemView.awayTeam.text = eventFavorite.strAwayTeam
            itemView.scoreAway.text = eventFavorite.intAwayScore
            itemView.setOnClickListener {
                listener(eventFavorite)
            }

        }
    }
}
