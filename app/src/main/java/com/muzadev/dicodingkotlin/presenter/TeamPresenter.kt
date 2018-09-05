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
class TeamPresenter(private val view: TeamView) {
    val repository = ApiRepository()
    val gson = Gson()

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
}