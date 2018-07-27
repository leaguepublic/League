package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewHolderFactory
import com.welab.league.listener.OnReloadListener

class LocalFilterItemInfo(onReloadListener: OnReloadListener) : BaseItemInfo(){
    val reloadListener: OnReloadListener = onReloadListener;

    override fun getType(): Int {
        return ViewHolderFactory.VIEW_TYPE_LOCAL_FILTER
    }
}