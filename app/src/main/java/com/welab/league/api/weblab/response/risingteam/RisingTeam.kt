package com.welab.league.api.weblab.response.risingteam

import com.welab.league.api.weblab.response.CategoryInfo
import com.welab.league.factory.ViewFactory

data class RisingTeam(var TeamName:String,
                      var TeamEmblemImageUrl:String,
                      var TeamStats:List<String>,
                      var TeamMemberCount:String,
                      var TeamAge:String,
                      val TeamCode:String,
                      var GraphInfo:GraphInfo
                      ): CategoryInfo(TeamEmblemImageUrl) {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_RISING_TEAM
    }
}