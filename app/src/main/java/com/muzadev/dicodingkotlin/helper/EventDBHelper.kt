package com.muzadev.dicodingkotlin.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.muzadev.dicodingkotlin.model.EventTableConstant
import org.jetbrains.anko.db.*

/**
 * Created by zulfakar on 05/09/18.
 * For educational purposes
 */
class EventDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DatabaseProperties.DATABASE_EVENT_NAME, null, 1) {
    companion object {
        private var instance: EventDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): EventDBHelper {
            if (instance == null) {
                instance = EventDBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(EventTableConstant.TABLE_NAME, true,
                EventTableConstant.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                EventTableConstant.ID_EVENT to TEXT,
                EventTableConstant.EVENT_DATE to TEXT,
                EventTableConstant.HOME_TEAM to TEXT,
                EventTableConstant.HOME_SCORE to TEXT,
                EventTableConstant.AWAY_TEAM to TEXT,
                EventTableConstant.AWAY_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(EventTableConstant.TABLE_NAME, true)
    }

    val Context.eventDB: EventDBHelper
        get() = getInstance(applicationContext)

}