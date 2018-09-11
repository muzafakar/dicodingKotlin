package com.muzadev.dicodingkotlin.presenter

import com.muzadev.dicodingkotlin.model.Event

/**
 * Created by zulfakar on 30/08/18.
 * For educational purposes
 */
interface EventView : BaseView {
    fun showEventList(events: List<Event>)
}