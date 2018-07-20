package com.welab.league.api.response.weblab.matchresult

import com.welab.league.api.response.weblab.BaseItemInfo
import com.welab.league.factory.ViewHolderFactory

data class MatchResult(var MatchInfo:MatchInfo,
                       var TeamInfo:TeamInfo): BaseItemInfo() {

    init {
        type = ViewHolderFactory.VIEW_TYPE_MATCH_RESULT
    }
}