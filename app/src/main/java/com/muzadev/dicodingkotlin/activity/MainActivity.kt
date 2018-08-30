package com.muzadev.dicodingkotlin.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout.VERTICAL
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.R.color.colorAccent
import com.muzadev.dicodingkotlin.R.color.colorPrimary
import com.muzadev.dicodingkotlin.adapter.MainAdapter
import com.muzadev.dicodingkotlin.api.ApiRepository
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.presenter.MainPresenter
import com.muzadev.dicodingkotlin.presenter.MainView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter
    private lateinit var leagueName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        adapter = MainAdapter(teams)
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        val spinnerItem = resources.getStringArray(R.array.league)
        val spinerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItem)
        spinner.adapter = spinerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinnerItem[position]
                presenter.getTeamList(leagueName)
            }

        }

        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }


    }

    inner class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)
                spinner = spinner()
                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeResources(
                            android.R.color.holo_blue_light,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light
                    )
                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)
                        listTeam = recyclerView {
                            lparams(width = matchParent, height = wrapContent)
                            layoutManager = LinearLayoutManager(ctx)
                        }
                    }
                }
            }
        }

    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun showTeamList(teams: List<Team>) {
        swipeRefresh.isRefreshing = false
        this.teams.clear()
        this.teams.addAll(teams)
        adapter.notifyDataSetChanged()
    }

}

