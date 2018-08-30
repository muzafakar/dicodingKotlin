package com.muzadev.dicodingkotlin.api

import java.net.URL

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
class ApiRepository {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}