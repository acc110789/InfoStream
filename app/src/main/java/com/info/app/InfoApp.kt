package com.info.app

import com.facebook.stetho.Stetho
import com.info.base.BaseApp
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * Created by zhangxiaolong on 18/2/26.
 */

class InfoApp : BaseApp() {
    companion object {
        private val GLOBAL_LOG_TAG = "InfoApp"
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        Logger.addLogAdapter(AndroidLogAdapter(PrettyFormatStrategy.newBuilder().tag(GLOBAL_LOG_TAG).build()))
    }
}
