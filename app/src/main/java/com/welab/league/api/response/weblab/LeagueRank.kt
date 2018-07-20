package com.welab.league.api.response.weblab

import com.welab.league.factory.ViewHolderFactory

data class LeagueRank(var Rank:String,
                      var TeamName:String,
                      var TeamEmblemImageUrl:String,
                      var MatchHistory:String
                      ): BaseItemInfo() {

    init {
        type = ViewHolderFactory.VIEW_TYPE_LEAGUE_RANK
    }
}
