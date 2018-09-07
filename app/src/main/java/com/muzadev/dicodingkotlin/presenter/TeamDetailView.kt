package com.muzadev.dicodingkotlin.presenter

import com.muzadev.dicodingkotlin.model.Team

/**
 * Created by zulfakar on 06/09/18.
 * For educational purposes
 */
interface TeamDetailView : BaseView {
    fun showTeamDetail(team: Team)
}