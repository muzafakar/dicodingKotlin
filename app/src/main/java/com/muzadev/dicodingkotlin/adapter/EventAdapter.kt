package com.muzadev.dicodingkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.model.Event
import kotlinx.android.synthetic.main.item_match.view.*

/**
 * Created by zulfakar on 07/09/18.
 * For educational purposes
 */
class EventAdapter(private val context: Context, private val events: List<Event>, private val listener: (Event) -> Unit) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))


    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(event: Event, listener: (Event) -> Unit) {

            itemView.matchDate.text = event.dateEvent
            itemView.homeTeam.text = event.strHomeTeam
            itemView.scoreHome.text = event.intHomeScore
            itemView.awayTeam.text = event.strAwayTeam
            itemView.scoreAway.text = event.intAwayScore
            itemView.setOnClickListener {
                listener(event)
            }

        }
    }
}