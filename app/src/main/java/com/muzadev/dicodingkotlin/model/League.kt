package com.muzadev.dicodingkotlin.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by zulfakar on 01/09/18.
 * For educational purposes
 */
data class League(
        @SerializedName("idLeague")
        val leagueId: String? = null,

        @SerializedName("strLeague")
        val leagueName: String? = null,

        @SerializedName("strLeagueAlternate")
        val alternateName: String? = null,

        @SerializedName("strSport")
        val type: String? = null
) : Serializable

data class LeagueResponse(val leagues: List<League>)