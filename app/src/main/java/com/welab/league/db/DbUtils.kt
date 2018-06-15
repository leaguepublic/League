package com.welab.league.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbUtils private constructor(context: Context){
    var db: SQLiteDatabase

    init {
        db = DbHelper(context).writableDatabase
    }

    companion object {
        @JvmField val TABLE_NAME = "keyword"

        @JvmField val COLUMN_ID = "_id"
        @JvmField val COLUMN_KEYWORD = "keyword"
        @JvmField val COLUMN_DATE = "date"

        @JvmField val COLUMNS_KEYWORD = arrayOf(COLUMN_ID, COLUMN_KEYWORD, COLUMN_DATE)

        @JvmField val COLUMN_INDEX_ID = 0
        @JvmField val COLUMN_INDEX_KEYWORD = 1
        @JvmField val COLUMN_INDEX_DATE = 2

        @JvmStatic fun getInstance(context: Context): DbUtils {
            return DbUtils(context)
        }
    }

    fun insertKeyword(keyword: String): Long {
        val values = ContentValues()
        values.put(COLUMN_KEYWORD, keyword)
        values.put(COLUMN_DATE, System.currentTimeMillis())

        return db.insert(TABLE_NAME, null, values)
    }

    fun selectKeywordInfo(): MutableList<String> {
        var keywordList = mutableListOf<String>()

        val cursor = db.query(TABLE_NAME, COLUMNS_KEYWORD, null, null, null, null, COLUMN_DATE + " ASC")

        while(cursor.moveToNext() == true) {
            keywordList.add(cursor.getString(COLUMN_INDEX_KEYWORD))
        }

        return keywordList
    }

    fun deleteKeyword(keyword: String): Int {
        return db.delete(TABLE_NAME, "${COLUMN_KEYWORD} = '${keyword}'", null)
    }

    fun deleteAllKeywords(): Int {
        return db.delete(TABLE_NAME, null, null)
    }
}