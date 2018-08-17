package com.welab.league.api.weblab.response

import com.welab.league.factory.ViewFactory
import com.welab.league.listener.OnReloadListener

data class LocalFilterItemInfo(val title:String) : BaseItemInfo(){
    var reloadListener: OnReloadListener? = null

    override fun getType(): Int {
        return ViewFactory.VIEW_TYPE_LOCAL_FILTER
    }
}