package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewFactory

class DividerInfo(): BaseItemInfo() {

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_DIVIDER
    }
}