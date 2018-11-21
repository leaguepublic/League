package com.welab.league.widget

import android.support.v4.app.Fragment

open class BaseFragment<T>:Fragment() {

    private var mData:T? = null

    fun setData(data: T) {
        mData = data;
    }

    fun getData(): T? {
        return mData
    }
}