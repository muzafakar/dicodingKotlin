package com.muzadev.dicodingkotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.activity.EventDetailActivity
import com.muzadev.dicodingkotlin.adapter.FavoriteEventAdapter
import com.muzadev.dicodingkotlin.helper.EventDBHelper
import com.muzadev.dicodingkotlin.model.EventFavorite
import com.muzadev.dicodingkotlin.model.EventTableConstant
import kotlinx.android.synthetic.main.fragment_common_layout.view.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by zulfakar on 07/09/18.
 * For educational purposes
 */

class FavoriteMatchFragment : Fragment() {
    private lateinit var eventAdapter: FavoriteEventAdapter
    private var eventFavorite: MutableList<EventFavorite> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventAdapter = FavoriteEventAdapter(activity!!.applicationContext, eventFavorite) {
            startActivity<EventDetailActivity>("eventId" to it.idEvent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_common_layout, container, false)
        recyclerView = view.recyclerView

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = eventAdapter

        swipeRefreshLayout = view.swipeRefresh
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        )

        swipeRefreshLayout.onRefresh {
            getFavoriteEvent()
        }
        getFavoriteEvent()
        return view
    }

    private fun getFavoriteEvent() {
        EventDBHelper.getInstance(activity!!.applicationContext).use {
            val queryResult = select(EventTableConstant.TABLE_NAME)
            val favoriteEvent = queryResult.parseList(classParser<EventFavorite>())
            eventFavorite.clear()
            eventFavorite.addAll(favoriteEvent)
            eventAdapter.notifyDataSetChanged()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
