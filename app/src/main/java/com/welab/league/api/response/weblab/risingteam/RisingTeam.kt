package com.welab.league.api.response.weblab.risingteam

import com.welab.league.api.response.weblab.BaseItemInfo
import com.welab.league.factory.ViewHolderFactory

data class RisingTeam(var TeamName:String,
                      var TeamEmblemImageUrl:String,
                      var MatchHistory:String,
                      var TeamMemberCount:String,
                      var MeanAge:String,
                      var GraphInfo:GraphInfo
                      ): BaseItemInfo() {

    init {
        type = ViewHolderFactory.VIEW_TYPE_RISING_TEAM
    }
}