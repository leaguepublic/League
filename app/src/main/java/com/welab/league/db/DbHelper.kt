package com.welab.league.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.welab.league.db.DbUtils.Companion.COLUMNS_KEYWORD
import com.welab.league.db.DbUtils.Companion.COLUMN_DATE
import com.welab.league.db.DbUtils.Companion.COLUMN_ID
import com.welab.league.db.DbUtils.Companion.TABLE_NAME

private val DB_VERSION: Int = 1
private val DB_NAME: String = "league.db";

class DbHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    val CREATE_TABLE_SEARCH_KEYWORD: String = "CREATE TABLE ${TABLE_NAME} (" +
            "${COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${COLUMNS_KEYWORD} TEXT," +
            "${COLUMN_DATE} TEXT" +
            ")"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_SEARCH_KEYWORD)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}