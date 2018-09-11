package com.muzadev.dicodingkotlin.presenter

import com.google.gson.Gson
import com.muzadev.dicodingkotlin.api.ApiRepository
import com.muzadev.dicodingkotlin.api.SportDBApi
import com.muzadev.dicodingkotlin.model.Event
import com.muzadev.dicodingkotlin.model.EventResponse
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.model.TeamResonse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class Presenter<in T>(private val view: T) {
    private val repository = ApiRepository()
    private val gson = Gson()

    fun getEventDetail(eventId: String?) {
        if (view is EventDetailView) {
            view.showLoading()
            doAsync {
                val data: Event? = gson.fromJson(repository.doRequest(SportDBApi.getEventDetail(eventId)),
                        EventResponse::class.java).events[0]

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showEventDetail(it1) }
                }
            }
        }
    }

    fun getNextMatch(leagueId: String?) {
        if (view is EventView) {
            view.showLoading()
            doAsync {
                val data: List<Event>? = gson.fromJson(repository.doRequest(SportDBApi.getNextMatches(leagueId)),
                        EventResponse::class.java).events

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showEventList(it1) }
                }
            }
        }
    }

    fun getPrevMatch(leagueId: String?) {
        if (view is EventView) {
            view.showLoading()
            doAsync {
                val data: List<Event>? = gson.fromJson(repository.doRequest(SportDBApi.getPrevMatches(leagueId)),
                        EventResponse::class.java).events

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showEventList(it1) }
                }
            }
        }
    }


    fun getTeamList(league: String?) {
        if (view is TeamView) {
            view.showLoading()
            doAsync {
                val data: List<Team>? = gson.fromJson(repository.doRequest(SportDBApi.getTeams(league)),
                        TeamResonse::class.java
                ).teams

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showTeamList(it1) }
                }
            }
        }
    }

    fun getSepecificTeam(teamName: String?) {
        if (view is TeamDetailView) {
            view.showLoading()
            doAsync {
                val data: Team? = gson.fromJson(repository.doRequest(SportDBApi.getSpecificTeam(teamName)),
                        TeamResonse::class.java).teams[0]

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showTeamDetail(it1) }

                }
            }
        }
        if (view is EventDetailView) {
            view.showLoading()
            doAsync {
                val data: Team? = gson.fromJson(repository.doRequest(SportDBApi.getSpecificTeam(teamName)),
                        TeamResonse::class.java).teams[0]

                uiThread {
                    view.hideLoading()
                    data?.let { it1 -> view.showTeamEmblem(it1) }

                }
            }
        }

    }
}