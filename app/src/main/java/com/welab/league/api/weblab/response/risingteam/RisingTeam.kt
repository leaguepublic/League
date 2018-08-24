package com.welab.league.api.weblab.response.risingteam

import com.welab.league.api.weblab.response.BaseItemInfo
import com.welab.league.factory.ViewFactory

data class RisingTeam(var TeamName:String,
                      var TeamEmblemImageUrl:String,
                      var TeamStats:List<String>,
                      var TeamMemberCount:String,
                      var TeamAge:String,
                      var GraphInfo:GraphInfo
                      ): BaseItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_RISING_TEAM
    }
}