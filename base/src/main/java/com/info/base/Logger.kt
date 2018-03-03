package com.info.base

import android.util.Log
import java.lang.IllegalStateException
import java.lang.RuntimeException

/**
 * Created by zhangxiaolong on 18/3/1.
 */
object Logger {

    private val TAG = "Logger"

    internal var mLevel = Log.INFO

    fun debug(): Boolean {
        return mLevel <= Log.DEBUG
    }

    fun throwException(exception: Throwable?) {
        if (exception == null) {
            return
        }
        exception.printStackTrace()
        if (Logger.debug()) {
            throw RuntimeException("Error! Now in debug, we alert to you to correct it !", exception)
        }
    }

    fun alertErrorInfo(errorMsg: String) {
        if (Logger.debug()) {
            throw IllegalStateException(errorMsg)
        }
    }

    fun w(tag : String, msg : String?) {
        Log.w(tag , msg)
    }
}