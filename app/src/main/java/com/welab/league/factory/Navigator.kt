package com.welab.league.factory

import android.content.Context
import android.util.Log
import com.welab.league.factory.ViewFactory.VIEW_TYPE_MATCH_TEAM
import com.welab.league.factory.ViewFactory.VIEW_TYPE_NEW_JOINING_TEAM

class Navigator {

    companion object {

        @JvmStatic fun callActivity(context: Context, type: Int) {
            when(type) {
                VIEW_TYPE_MATCH_TEAM -> {
                    Log.e("TAG", "LJS== call Match Team Activity ==");
                }

                VIEW_TYPE_NEW_JOINING_TEAM -> {
                    Log.e("TAG", "LJS== call New Joinning Team Activity ==");
                }
            }
        }

        @JvmStatic fun requestMatch(teamCode:String) {

        }
    }
}