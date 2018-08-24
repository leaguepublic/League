package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewFactory

data class RecentKeywordInfo(val keyword:String): BaseItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_RECENT_KEYWORD
    }
}