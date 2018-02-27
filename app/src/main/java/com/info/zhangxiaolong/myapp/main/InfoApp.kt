package com.info.zhangxiaolong.myapp.main

import android.app.Application

/**
 * Created by zhangxiaolong on 18/2/26.
 */

class InfoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        sInstance = this
    }

    companion object {
        private lateinit var sInstance: InfoApp
        fun inst() : InfoApp {
            return sInstance
        }
    }
}
