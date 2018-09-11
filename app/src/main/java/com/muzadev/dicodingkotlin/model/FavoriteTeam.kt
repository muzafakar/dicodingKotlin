package com.muzadev.dicodingkotlin.model

import java.io.Serializable

/**
 * Created by zulfakar on 05/09/18.
 * For educational purposes
 */
data class TeamFavorite(val id: Long?,
                        val teamId: String?,
                        val teamName: String?,
                        val teamBadge: String?
) : Serializable

object TeamTableConstant {
    const val TABLE_NAME = "Team_Favorite"
    const val ID = "ID"
    const val TEAM_NAME = "TEAM_NAME"
    const val TEAM_ID = "TEAM_ID"
    const val TEAM_BADGE = "TEAM_BADGE"
}