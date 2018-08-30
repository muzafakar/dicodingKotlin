package com.muzadev.dicodingkotlin.presenter

import com.muzadev.dicodingkotlin.model.Team

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(teams: List<Team>)
}