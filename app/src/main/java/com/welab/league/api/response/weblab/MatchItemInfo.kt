package com.welab.league.api.response.weblab

import com.welab.league.factory.ViewHolderFactory

class MatchItemInfo : BaseItemInfo() {

    init {
        super.type = ViewHolderFactory.VIEW_TYPE_MATCH
    }
}