package com.muzadev.dicodingkotlin.presenter

import com.google.gson.Gson
import com.muzadev.dicodingkotlin.api.ApiRepository
import com.muzadev.dicodingkotlin.api.SportDBApi
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.model.TeamResonse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class Presenter<in T>(private val view: T) {
    val repository = ApiRepository()
    val gson = Gson()

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

    }
}