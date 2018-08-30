package com.muzadev.dicodingkotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
data class Team(
        @SerializedName("idTeam")
        val teamId: String? = null,

        @SerializedName("strTeam")
        val teamName: String? = null,

        @SerializedName("strTeamBadge")
        val teamBadge: String? = null
)

data class TeamResonse(val teams: List<Team>)