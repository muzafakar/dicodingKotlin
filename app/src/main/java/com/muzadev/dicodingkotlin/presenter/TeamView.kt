package com.muzadev.dicodingkotlin.presenter

import com.muzadev.dicodingkotlin.model.Team

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
interface TeamView : BaseView{
    fun showTeamList(teams: List<Team>)
}