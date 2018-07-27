package com.welab.league.api.weblab.response

import com.welab.league.api.weblab.response.matchresult.MatchResult
import com.welab.league.api.weblab.response.risingteam.RisingTeam
import com.welab.league.factory.ViewHolderFactory

data class TabHome(var MatchTeamInfo: ArrayList<MatchTeamInfo>,
                   var MatchResultBallot: ArrayList<MatchResultBallot>,
                   var MatchResult:ArrayList<MatchResult>,
                   var NewJoiningTeam: ArrayList<NewJoiningTeam>,
                   var RisingTeam: ArrayList<RisingTeam>,
                   var LeagueRank: ArrayList<LeagueRank>
                   ): BaseItemInfo() {

    fun getList(): ArrayList<List<BaseItemInfo>> {
        return arrayListOf<List<BaseItemInfo>>(MatchTeamInfo, MatchResultBallot, MatchResult, NewJoiningTeam, RisingTeam, LeagueRank)
    }

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_TABHOME
    }
}
