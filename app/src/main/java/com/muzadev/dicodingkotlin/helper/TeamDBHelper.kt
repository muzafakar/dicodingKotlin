package com.muzadev.dicodingkotlin.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.muzadev.dicodingkotlin.model.TeamTableConstant
import org.jetbrains.anko.db.*

/**
 * Created by zulfakar on 05/09/18.
 * For educational purposes
 */
class TeamDBHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, DatabaseProperties.DATABASE_TEAM_NAME, null, 1) {
    companion object {
        private var instance: TeamDBHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): TeamDBHelper {
            if (instance == null) {
                instance = TeamDBHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(TeamTableConstant.TABLE_NAME, true,
                TeamTableConstant.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamTableConstant.TEAM_ID to TEXT,
                TeamTableConstant.TEAM_NAME to TEXT,
                TeamTableConstant.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(TeamTableConstant.TABLE_NAME, true)
    }

    val Context.teamDB: TeamDBHelper
        get() = getInstance(applicationContext)
}