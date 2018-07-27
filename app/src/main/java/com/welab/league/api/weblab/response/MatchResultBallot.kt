package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewHolderFactory

data class MatchResultBallot(var TeamName:String,
                             var TeamEmblemImageUrl:String,
                             var PrivateMatchResult:String,
                             var PublicMatchResult:String,
                             var Vote:String): BaseItemInfo() {

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_MATCH_RESULT_BALLOT
    }
}