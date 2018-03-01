package com.info.base

import android.app.Application

/**
 * Created by zhangxiaolong on 18/3/1.
 */
open class BaseApp : Application(){
    companion object {
        private lateinit var sInst : BaseApp
        fun inst() : BaseApp {
            return sInst
        }
    }

    override fun onCreate() {
        super.onCreate()
        sInst = this
    }
}