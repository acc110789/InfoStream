package com.info.base

import android.util.TypedValue

/**
 * Created by zhangxiaolong on 18/3/1.
 */
object UIUtils {
    fun sp2px(sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, BaseApp.inst().resources.displayMetrics)
    }

    fun dip2Px(dipValue: Float): Float {
        val scale = BaseApp.inst().resources.displayMetrics.density
        return dipValue * scale + 0.5f
    }

    fun px2dip(pxValue: Float): Int {
        val scale = BaseApp.inst().resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    fun getScreenWidth(): Int {
        return BaseApp.inst().resources.displayMetrics?.widthPixels ?: 0
    }


    fun getScreenHeight(): Int {
        return BaseApp.inst().resources.displayMetrics?.heightPixels ?: 0
    }

    fun getDpi(): Int {
        return BaseApp.inst().applicationContext?.resources?.displayMetrics?.densityDpi ?: -1
    }

    fun getStatusBarHeight(): Int {
        var result = 0
        val context = BaseApp.inst()
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}