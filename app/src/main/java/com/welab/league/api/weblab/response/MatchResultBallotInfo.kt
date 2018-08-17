package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewFactory

data class MatchResultBallotInfo(var TeamName:String,
                                 var TeamEmblemImageUrl:String,
                                 var PrivateMatchResult:String,
                                 var PublicMatchResult:String,
                                 var Vote:String): ViewPagerItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_MATCH_RESULT_BALLOT
    }

    override fun getTitle(): String {
        return "경기 예측"
    }
}