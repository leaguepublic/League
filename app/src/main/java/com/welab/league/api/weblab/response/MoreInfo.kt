package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewFactory

data class MoreInfo(val callType:Int
                      ): BaseItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_MORE
    }
}
