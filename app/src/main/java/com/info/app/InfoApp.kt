package com.info.app

import com.facebook.stetho.Stetho
import com.info.base.BaseApp
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger



/**
 * Created by zhangxiaolong on 18/2/26.
 */

class InfoApp : BaseApp() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}
