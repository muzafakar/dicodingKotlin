package com.muzadev.dicodingkotlin.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.activity.TeamDetailActivity
import com.muzadev.dicodingkotlin.adapter.TeamAdapter
import com.muzadev.dicodingkotlin.model.Event
import com.muzadev.dicodingkotlin.model.League
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.presenter.Presenter
import com.muzadev.dicodingkotlin.presenter.TeamView
import kotlinx.android.synthetic.main.fragment_teams.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh


class TeamsFragment : Fragment(), TeamView {

    private lateinit var adapter: TeamAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private var teamList: MutableList<Team> = mutableListOf()
    private var leagueList: MutableList<String> = mutableListOf()
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var swRefresh: SwipeRefreshLayout
    private lateinit var presenter: Presenter
    private var leagueName = "English Premiere league" //default league

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leagueList.addAll(resources.getStringArray(R.array.league))
        presenter = Presenter(this)
        spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, leagueList)
        adapter = TeamAdapter(ctx, teamList) {
            ctx.startActivity(intentFor<TeamDetailActivity>("team" to it))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_teams, container, false)
        swRefresh = view.swipeRefresh
        swRefresh.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light
        )
        swRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
        view.spinnerLeague.adapter = spinnerAdapter
        view.spinnerLeague.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinnerAdapter.getItem(position).toString()
                presenter.getTeamList(leagueName)
            }

        }
        view.rvTeams.adapter = adapter
        view.rvTeams.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        presenter.getTeamList(leagueName)
        return view
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showLoading() {
        swRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swRefresh.isRefreshing = false
    }

    override fun showLeagueList(leagues: List<League>) {}

    override fun showTeamList(teams: List<Team>) {
        teamList.clear()
        teamList.addAll(teams)
        adapter.notifyDataSetChanged()
    }

    override fun showMatchList(events: List<Event>) {}


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

}
