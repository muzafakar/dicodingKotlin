package com.muzadev.dicodingkotlin.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.muzadev.dicodingkotlin.model.TableConstant
import org.jetbrains.anko.db.*

/**
 * Created by zulfakar on 05/09/18.
 * For educational purposes
 */
class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, TableConstant.DATBASE_NAME, null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }


    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(TableConstant.TABLE_NAME, true,
                TableConstant.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TableConstant.TEAM_ID to TEXT,
                TableConstant.TEAM_NAME to TEXT,
                TableConstant.TEAM_BADGE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(TableConstant.TABLE_NAME, true)
    }

    val Context.database: DatabaseHelper
        get() = getInstance(applicationContext)
}