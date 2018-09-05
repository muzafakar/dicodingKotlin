package com.muzadev.dicodingkotlin.model

/**
 * Created by zulfakar on 05/09/18.
 * For educational purposes
 */
data class Favorite(val id: Long?,
                    val teamId: String?,
                    val teamName: String?,
                    val teamBadge: String?,
                    val teamDesc: String?,
                    val teamStadium: String?
)

object TableConstant {
    const val TABLE_NAME = "Table Favorite"
    const val ID = "ID"
    const val TEAM_NAME = "TEAM_NAME"
    const val TEAM_ID = "TEAM_ID"
    const val TEAM_BADGE = "TEAM_BADGE"
    const val TEAM_DESC = "TEAM_DESC"
    const val TEAM_STADIUM = "TEAM_STADIUM"
}