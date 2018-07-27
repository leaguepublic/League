package com.welab.league.api.weblab.response.risingteam

import com.welab.league.api.weblab.response.BaseItemInfo
import com.welab.league.factory.ViewHolderFactory

data class RisingTeam(var TeamName:String,
                      var TeamEmblemImageUrl:String,
                      var MatchHistory:String,
                      var TeamMemberCount:String,
                      var MeanAge:String,
                      var GraphInfo:GraphInfo
                      ): BaseItemInfo() {

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_RISING_TEAM
    }
}