package com.muzadev.dicodingkotlin.api

import android.net.Uri
import com.muzadev.dicodingkotlin.BuildConfig

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
object SportDBApi {
    private val BASE_URL = "https://www.thesportsdb.com/"

    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }
}