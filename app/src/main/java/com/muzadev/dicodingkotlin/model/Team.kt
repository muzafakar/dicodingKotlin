package com.muzadev.dicodingkotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
        val teamBadge: String? = null,

        @SerializedName("strDescriptionEN")
        val temDescription: String? = null,

        @SerializedName("intFormedYear")
        val formedYear: Int? = null,

        @SerializedName("strStadium")
        val stadium: String? = null
) : Serializable

data class TeamResonse(val teams: List<Team>)