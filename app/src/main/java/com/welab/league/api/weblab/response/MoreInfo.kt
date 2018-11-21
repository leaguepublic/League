package com.welab.league.api.weblab.response

data class MoreInfo(val targetType:Int,
                    val moreType:Int
                      ): BaseItemInfo() {

    override fun getType(): Int {
        return moreType
    }
}
