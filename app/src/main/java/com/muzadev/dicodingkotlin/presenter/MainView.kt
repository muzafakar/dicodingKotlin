package com.muzadev.dicodingkotlin.presenter

import com.muzadev.dicodingkotlin.model.Event
import com.muzadev.dicodingkotlin.model.League
import com.muzadev.dicodingkotlin.model.Team

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(leagues: List<League>)
    fun showTeamList(teams: List<Team>)
    fun showMatchList(events: List<Event>)
}