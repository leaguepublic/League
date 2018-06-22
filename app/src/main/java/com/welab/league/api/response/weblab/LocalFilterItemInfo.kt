package com.welab.league.api.response.weblab

import com.welab.league.factory.ViewHolderFactory
import com.welab.league.listener.OnReloadListener

class LocalFilterItemInfo(onReloadListener: OnReloadListener) : BaseItemInfo(){
    val reloadListener: OnReloadListener = onReloadListener;

    init {
        super.type = ViewHolderFactory.VIEW_TUPE_LOCAL_FILTER
    }

}