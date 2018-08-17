package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewFactory

data class NewJoiningTeamInfo(var TeamName:String,
                              var TeamEmblemImageUrl:String,
                              var TeamMemberCount:String,
                              var TeamAge:String,
                              var TeamGround:String,
                              var TeamCode:String
                          ): BaseItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_NEW_JOINING_TEAM
    }
}