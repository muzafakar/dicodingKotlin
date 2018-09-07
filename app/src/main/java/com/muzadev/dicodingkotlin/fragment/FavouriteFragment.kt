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
import com.muzadev.dicodingkotlin.adapter.FavoriteAdapter
import com.muzadev.dicodingkotlin.helper.DatabaseHelper
import com.muzadev.dicodingkotlin.model.Favorite
import com.muzadev.dicodingkotlin.model.TableConstant
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class FavouriteFragment : Fragment() {


    private lateinit var adapter: FavoriteAdapter
    private var favoriteList: MutableList<Favorite> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = FavoriteAdapter(activity!!.applicationContext, favoriteList) {
            ctx.startActivity(intentFor<TeamDetailActivity>("team" to it.teamName))
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        swipeRefreshLayout = view.swipeRefresh
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        )
        swipeRefreshLayout.onRefresh {
            getFavoriteTeam()
        }
        getFavoriteTeam()
        view.rvFavourite.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        view.rvFavourite.adapter = adapter
        return view
    }


    private fun getFavoriteTeam() {
        DatabaseHelper.getInstance(context!!).use {
            val queryResult = select(TableConstant.TABLE_NAME)
            val favoriteTeam = queryResult.parseList(classParser<Favorite>())
            favoriteList.clear()
            favoriteList.addAll(favoriteTeam)
            adapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }
    }

}
