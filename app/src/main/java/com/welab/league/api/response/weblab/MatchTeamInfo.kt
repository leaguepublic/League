package com.welab.league.api.response.weblab

import com.welab.league.factory.ViewHolderFactory

// https://stackoverflow.com/questions/44117970/kotlin-data-class-from-json-using-gson
data class MatchTeamInfo(var TeamName:String,
                         var UniformColor:String,
                         var MatchDate:String,
                         var StadiumName:String,
                         var MatchHistory:String,
                         var MeanAge:String,
                         var TeamType:String,
                         var Manner:String,
                         var Ranking:String
) : BaseItemInfo() {

    init {
        super.type = ViewHolderFactory.VIEW_TYPE_MATCH
    }
}