package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewFactory

data class LeagueRankInfo(var Rank:String,
                          var TeamName:String,
                          var TeamEmblemImageUrl:String,
                          var MatchHistory:String
                      ): BaseItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_LEAGUE_RANK
    }
}
