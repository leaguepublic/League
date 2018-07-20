package com.welab.league.api.response.weblab

import com.welab.league.factory.ViewHolderFactory

data class MatchResultBallot(var TeamName:String,
                             var TeamEmblemImageUrl:String,
                             var PrivateMatchResult:String,
                             var PublicMatchResult:String,
                             var Vote:String): BaseItemInfo() {

    init {
        super.type = ViewHolderFactory.VIEW_TYPE_MATCH_RESULT_BALLOT;
    }
}