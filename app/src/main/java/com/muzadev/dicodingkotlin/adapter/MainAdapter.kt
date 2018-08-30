package com.muzadev.dicodingkotlin.adapter

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout.HORIZONTAL
import android.widget.TextView
import com.bumptech.glide.Glide
import com.muzadev.dicodingkotlin.model.Team
import org.jetbrains.anko.*

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class MainAdapter(private val teams: List<Team>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    lateinit var TeamBadge: ImageView
    lateinit var TeamName: TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position])
    }


    inner class TeamUI : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = HORIZONTAL

                TeamBadge = imageView().lparams {
                    width = dip(50)
                    height = dip(50)
                }

                TeamName = textView {
                    textSize = 16f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    margin = dip(15)
                }
            }
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), AnkoLogger {
        private val teamBadge: ImageView = TeamBadge
        private val teamName: TextView = TeamName
        fun bindItem(team: Team) {
            info { "id:" + team.teamId }
            info { "name:" + team.teamName }
            info { "badge:" + team.teamBadge }
            Glide.with(itemView).load(team.teamBadge).into(teamBadge)
            teamName.text = team.teamName
        }
    }
}