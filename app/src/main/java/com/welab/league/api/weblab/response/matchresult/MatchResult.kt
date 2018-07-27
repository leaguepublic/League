package com.welab.league.api.weblab.response.matchresult

import com.welab.league.api.weblab.response.BaseItemInfo
import com.welab.league.factory.ViewHolderFactory

data class MatchResult(var MatchGround:String,
                       var MatchDate:String,
                       var Teams:List<Team>): BaseItemInfo() {

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_MATCH_RESULT
    }
}