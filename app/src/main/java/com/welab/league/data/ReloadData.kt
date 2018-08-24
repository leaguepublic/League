package com.welab.league.data

data class ReloadData(val type:Int,
                      val sortType:String,
                      val searchKeywordList:MutableList<String>) {

    companion object {
        const val TYPE_SORT = 0
        const val TYPE_SEARCH_KEYWORD_LIST = 1
    }
}