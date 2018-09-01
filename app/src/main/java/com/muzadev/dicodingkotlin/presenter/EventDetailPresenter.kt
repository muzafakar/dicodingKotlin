package com.muzadev.dicodingkotlin.presenter

import com.google.gson.Gson
import com.muzadev.dicodingkotlin.api.ApiRepository
import com.muzadev.dicodingkotlin.api.SportDBApi
import com.muzadev.dicodingkotlin.model.Team
import com.muzadev.dicodingkotlin.model.TeamResonse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by zulfakar on 01/09/18.
 * For educational purposes
 */
class EventDetailPresenter(val view: EventDetailView) {

    val repository = ApiRepository()
    val gson = Gson()

    fun getTeamBadge(teamName: String?) {
        doAsync {
            val team: List<Team> = gson.fromJson(repository.doRequest(SportDBApi.getSpecificTeam(teamName)),
                    TeamResonse::class.java).teams

            uiThread {
                team?.let { view.showTeamEmblem(it[0]) }
            }
        }
    }

}