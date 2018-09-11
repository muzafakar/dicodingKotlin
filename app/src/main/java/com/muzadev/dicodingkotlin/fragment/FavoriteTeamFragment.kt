package com.muzadev.dicodingkotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.activity.TeamDetailActivity
import com.muzadev.dicodingkotlin.adapter.FavoriteTeamAdapter
import com.muzadev.dicodingkotlin.helper.TeamDBHelper
import com.muzadev.dicodingkotlin.model.TeamFavorite
import com.muzadev.dicodingkotlin.model.TeamTableConstant
import kotlinx.android.synthetic.main.fragment_common_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class FavoriteTeamFragment : Fragment() {


    private lateinit var adapterTeam: FavoriteTeamAdapter
    private var teamFavoriteList: MutableList<TeamFavorite> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterTeam = FavoriteTeamAdapter(activity!!.applicationContext, teamFavoriteList) {
            ctx.startActivity(intentFor<TeamDetailActivity>("team" to it.teamName))
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_common_layout, container, false)
        swipeRefreshLayout = view.swipeRefresh
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        )

        getFavoriteTeam()
        swipeRefreshLayout.onRefresh {
            getFavoriteTeam()
        }

        view.recyclerView.layoutManager = LinearLayoutManager(context)
        view.recyclerView.adapter = adapterTeam

        getFavoriteTeam()
        return view
    }


    private fun getFavoriteTeam() {
        TeamDBHelper.getInstance(context!!).use {
            val queryResult = select(TeamTableConstant.TABLE_NAME)
            val favoriteTeam = queryResult.parseList(classParser<TeamFavorite>())
            teamFavoriteList.clear()
            teamFavoriteList.addAll(favoriteTeam)
            adapterTeam.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }
    }

}
