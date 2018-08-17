package com.welab.league.api.weblab.response.matchresult

import com.welab.league.api.weblab.response.ViewPagerItemInfo
import com.welab.league.factory.ViewFactory

data class MatchResult(var MatchGround:String,
                       var MatchDate:String,
                       var Teams:List<Team>): ViewPagerItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_MATCH_RESULT
    }

    override fun getTitle(): String {
        return "경기 결과"
    }
}