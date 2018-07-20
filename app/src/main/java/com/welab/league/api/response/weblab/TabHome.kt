package com.welab.league.api.response.weblab

import com.welab.league.api.response.weblab.risingteam.RisingTeam

data class TabHome(var MatchTeamInfo:MatchTeamInfo,
                   var MatchResultBallot:MatchResultBallot,
                   var MatchResult:MatchResult,
                   var NewJoiningTeam:NewJoiningTeam,
                   var RisingTeam: RisingTeam,
                   var LeagueRank:LeagueRank
                   ) {

    
}