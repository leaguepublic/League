package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewHolderFactory

data class LeagueRank(var Rank:String,
                      var TeamName:String,
                      var TeamEmblemImageUrl:String,
                      var MatchHistory:String
                      ): BaseItemInfo() {

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_LEAGUE_RANK
    }
}
