package com.muzadev.dicodingkotlin.presenter

import com.google.gson.Gson
import com.muzadev.dicodingkotlin.api.ApiRepository
import com.muzadev.dicodingkotlin.api.SportDBApi
import com.muzadev.dicodingkotlin.model.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class MainPresenter(private val view: MainView) {
    val repository = ApiRepository()
    val gson = Gson()
    var leagues: MutableList<League> = mutableListOf()

    fun getLeagueList() {
        view.showLoading()
        doAsync {
            val data: List<League>? = gson.fromJson(repository.doRequest(SportDBApi.getLeagues()),
                    LeagueResponse::class.java).leagues

            for (league in data!!.iterator()) {
                if (league.type.equals("Soccer")) {
                    leagues.add(league)
                }
            }

            uiThread {
                view.hideLoading()
                data?.let { view.showLeagueList(leagues) }
            }
        }
    }

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data: List<Team>? = gson.fromJson(repository.doRequest(SportDBApi.getTeams(league)),
                    TeamResonse::class.java
            ).teams

            uiThread {
                view.hideLoading()
                data?.let { view.showTeamList(data) }
            }
        }
    }

    fun getLastMatch(leagueId: String?) {
        view.showLoading()
        doAsync {
            val data: List<Event>? = gson.fromJson(repository.doRequest((SportDBApi.getLastMatches(leagueId))),
                    EventResponse::class.java).events

            uiThread {
                view.hideLoading()
                data?.let { view.showMatchList(data) }
            }
        }
    }

    fun getNextMatch(leagueId: String?) {
        view.showLoading()
        doAsync {
            val data: List<Event>? = gson.fromJson(repository.doRequest(SportDBApi.getNextMatches(leagueId)),
                    EventResponse::class.java).events

            uiThread {
                view.hideLoading()
                data?.let { it -> view.showMatchList(it) }
            }
        }
    }
}