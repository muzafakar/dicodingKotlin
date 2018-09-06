package com.muzadev.dicodingkotlin.model

import java.io.Serializable

/**
 * Created by zulfakar on 05/09/18.
 * For educational purposes
 */
data class Favorite(val id: Long?,
                    val teamId: String?,
                    val teamName: String?,
                    val teamBadge: String?,
                    val teamDesc: String?,
                    val teamStadium: String?,
                    val formedYear: Int?
) : Serializable

object TableConstant {
    const val DATBASE_NAME = "FavoriteTeam.db"
    const val TABLE_NAME = "Table_Favorite"
    const val ID = "ID"
    const val TEAM_NAME = "TEAM_NAME"
    const val TEAM_ID = "TEAM_ID"
    const val TEAM_BADGE = "TEAM_BADGE"

}