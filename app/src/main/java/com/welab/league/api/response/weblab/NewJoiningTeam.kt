package com.welab.league.api.response.weblab

import com.welab.league.factory.ViewHolderFactory

data class NewJoiningTeam(var TeamName:String,
                          var TeamEmblemImageUrl:String,
                          var TeamMemberCount:String,
                          var MeanAge:String,
                          var StadiumName:String
                          ): BaseItemInfo() {

    init {
        type = ViewHolderFactory.VIEW_TYPE_NEW_JOINING_TEAM
    }
}