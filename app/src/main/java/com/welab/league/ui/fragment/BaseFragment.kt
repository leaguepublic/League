package com.welab.league.ui.fragment

import android.support.v4.app.Fragment

abstract class BaseFragment<T>:Fragment() {

    abstract fun setData(mutableList: MutableList<T>)
}