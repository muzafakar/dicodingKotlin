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
class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data: List<Team>? = gson.fromJson(apiRepository.doRequest(SportDBApi.getTeams(league)),
                    TeamResonse::class.java
            ).teams

            uiThread {
                view.hideLoading()
                data?.let { it1 -> view.showTeamList(it1) }
            }
        }
    }
}