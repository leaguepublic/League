package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewHolderFactory

data class NewJoiningTeam(var TeamName:String,
                          var TeamEmblemImageUrl:String,
                          var TeamMemberCount:String,
                          var TeamAge:String,
                          var TeamGround:String,
                          var TeamCode:String
                          ): BaseItemInfo() {

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_NEW_JOINING_TEAM
    }
}