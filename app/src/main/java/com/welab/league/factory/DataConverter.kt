package com.welab.league.factory

import android.content.Context
import com.welab.league.R
import com.welab.league.api.weblab.response.BaseItemInfo
import com.welab.league.api.weblab.response.LocalFilterItemInfo
import com.welab.league.api.weblab.response.MoreInfo
import com.welab.league.api.weblab.response.RecentKeywordInfo

class DataConverter {

    companion object {

        val MAX_COUNT_MATCH_TEAM = 5
        val MAX_COUNT_NEW_JOINING_TEAM = 5

        @JvmStatic fun convert(context: Context, dataList: MutableList<BaseItemInfo>): MutableList<BaseItemInfo> {
            when (dataList.get(0).getType()) {
                ViewFactory.VIEW_TYPE_MATCH_TEAM -> {
                    fixData(dataList, ViewFactory.VIEW_TYPE_MATCH_TEAM, MAX_COUNT_MATCH_TEAM)
                    dataList.add(0, LocalFilterItemInfo(context.getString(R.string.tab_match), false, true))
                }

                ViewFactory.VIEW_TYPE_NEW_JOINING_TEAM -> {
                    fixData(dataList, ViewFactory.VIEW_TYPE_NEW_JOINING_TEAM, MAX_COUNT_NEW_JOINING_TEAM)
                    dataList.add(0, LocalFilterItemInfo(context.getString(R.string.new_joinning_team), false, false))
                }
            }

            return dataList
        }

        private fun fixData(dataList: MutableList<BaseItemInfo>, viewType:Int, maxCount: Int) {
            var currentCount = 0

            for (elementList:BaseItemInfo in dataList) {
                if (currentCount < maxCount && elementList.getType() == viewType) {
                    currentCount++

                    if (currentCount >= maxCount) {
                        val elementSize = dataList.size -1

                        for (index in maxCount..elementSize) {
                            dataList.removeAt(maxCount)
                        }

                        dataList.add(MoreInfo(dataList.get(0).getType(), ViewFactory.VIEW_TYPE_MORE))

                        break
                    }
                }
            }
        }

        @JvmStatic fun getRecentKeywordList(dataList: MutableList<String>): MutableList<BaseItemInfo> {
            val converDataList: MutableList<BaseItemInfo> = ArrayList<BaseItemInfo>()

            for (value:String in dataList) {
                converDataList.add(RecentKeywordInfo(value))
            }

            return converDataList
        }
    }
}