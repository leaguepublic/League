package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewHolderFactory

// https://stackoverflow.com/questions/44117970/kotlin-data-class-from-json-using-gson
data class MatchTeamInfo(var TeamName:String,
                         var TeamColor:String,
                         var MatchDate:String,
                         var MatchGround:String,
                         var TeamStats:List<String>,
                         var TeamAge:String,
                         var TeamType:String,
                         var TeamManner:String,
                         var TeamRanking:String,
                         var TeamCode:String
) : BaseItemInfo() {

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_MATCH
    }
}