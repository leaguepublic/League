package com.welab.league.widget

import android.support.v7.widget.RecyclerView
import android.view.View

open class BaseViewHolder<T>(var itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun setData(data: T) {}

    open fun setListData(listData: MutableList<T>) {}
}