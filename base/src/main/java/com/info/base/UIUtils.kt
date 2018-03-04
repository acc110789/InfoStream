package com.info.base

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import java.lang.Exception

/**
 * Created by zhangxiaolong on 18/3/1.
 */
object UIUtils {
    fun sp2px(context: Context, sp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
    }

    fun dip2Px(context: Context, dipValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dipValue * scale + 0.5f
    }

    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun setViewBackgroundWithPadding(view: View?, resid: Int) {
        view?.let {
            val left = it.paddingLeft
            val right = it.paddingRight
            val top = it.paddingTop
            val bottom = it.paddingBottom
            it.setBackgroundResource(resid)
            it.setPadding(left, top, right, bottom)
        }
    }

    fun setViewBackgroundWithPadding(view: View?, res: Resources, colorid: Int) {
        view?.let {
            val left = it.paddingLeft
            val right = it.paddingRight
            val top = it.paddingTop
            val bottom = it.paddingBottom
            it.setBackgroundColor(res.getColor(colorid))
            it.setPadding(left, top, right, bottom)
        }
    }

    fun setViewBackgroundWithPadding(view: View?, drawable: Drawable) {
        view?.let {
            val left = it.paddingLeft
            val right = it.paddingRight
            val top = it.paddingTop
            val bottom = it.paddingBottom
            it.setBackgroundDrawable(drawable)
            it.setPadding(left, top, right, bottom)
        }
    }

    fun getScreenWidth(context: Context?): Int {
        return context?.let {
            val dm = context.resources.displayMetrics
            return dm?.widthPixels ?: 0
        } ?: 0
    }

    fun isInUIThread(): Boolean {
        return Looper.myLooper() == Looper.getMainLooper()
    }


    fun getScreenHeight(context: Context?): Int {
        return context?.let {
            it.resources.displayMetrics?.heightPixels ?: 0
        } ?: 0
    }

    private var DPI = -1

    fun getDpi(context: Context?): Int {
        if (DPI == -1) {
            DPI = context?.applicationContext?.resources?.displayMetrics?.densityDpi ?: -1
        }
        return DPI
    }

    fun getStatusBarHeight(context: Context?): Int {
        if (context == null) {
            return 0
        }
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}