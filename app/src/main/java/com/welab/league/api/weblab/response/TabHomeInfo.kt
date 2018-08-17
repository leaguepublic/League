package com.welab.league.api.weblab.response

import com.welab.league.api.weblab.response.matchresult.MatchResult
import com.welab.league.api.weblab.response.risingteam.RisingTeam
import com.welab.league.factory.ViewFactory

data class TabHomeInfo(var MatchTeamInfo: ArrayList<MatchTeamInfo>,
                       var MatchResultBallot: ArrayList<MatchResultBallotInfo>,
                       var MatchResult:ArrayList<MatchResult>,
                       var NewJoiningTeam: ArrayList<NewJoiningTeamInfo>,
                       var RisingTeam: ArrayList<RisingTeam>,
                       var LeagueRank: ArrayList<LeagueRankInfo>
                   ): BaseItemInfo() {

    fun getList(): ArrayList<List<BaseItemInfo>> {
        return arrayListOf<List<BaseItemInfo>>(MatchTeamInfo, MatchResultBallot, MatchResult, NewJoiningTeam, RisingTeam, LeagueRank)
    }

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_HOME
    }
}
