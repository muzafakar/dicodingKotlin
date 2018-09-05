package com.muzadev.dicodingkotlin.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.dicodingkotlin.R
import com.muzadev.dicodingkotlin.activity.TeamDetailActivity
import com.muzadev.dicodingkotlin.adapter.TeamAdapter
import com.muzadev.dicodingkotlin.model.Event
import com.muzadev.dicodingkotlin.model.League
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.presenter.TeamPresenter
import com.muzadev.dicodingkotlin.presenter.TeamView
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.android.synthetic.main.fragment_favourite.view.*
import org.jetbrains.anko.support.v4.startActivity

class FavouriteFragment : Fragment(), TeamView {


    private lateinit var adapter: TeamAdapter
    private var teamList: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamPresenter
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = TeamPresenter(this)
        adapter = TeamAdapter(activity!!.applicationContext, teamList){
            startActivity<TeamDetailActivity>("team" to it)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)
        view.rvFavourite.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        view.rvFavourite.adapter = adapter
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
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun showLeagueList(leagues: List<League>) {}

    override fun showTeamList(teams: List<Team>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}
