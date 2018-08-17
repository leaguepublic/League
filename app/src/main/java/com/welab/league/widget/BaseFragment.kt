package com.welab.league.widget

import android.support.v4.app.Fragment

open class BaseFragment<T>:Fragment() {

    open fun setData(data: T) {}

    open fun setData(mutableList: MutableList<T>) {}
}