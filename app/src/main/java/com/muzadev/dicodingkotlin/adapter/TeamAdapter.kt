package com.muzadev.dicodingkotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.model.Team
import kotlinx.android.synthetic.main.item_club.view.*

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class TeamAdapter(private val context: Context, private val teams: List<Team>, private val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater
            .from(context)
            .inflate(R.layout.item_club,
                    parent,
                    false)
    )


    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)

    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(team: Team, listener: (Team) -> Unit) {
            itemView.tvTeamName.text = team.teamName
            Glide.with(context).load(team.teamBadge).into(itemView.imgTeam)
            itemView.setOnClickListener {
                listener(team)
            }
        }
    }
}
