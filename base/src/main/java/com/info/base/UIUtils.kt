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

    fun isViewVisible(view: View?): Boolean {
        return view?.visibility == View.VISIBLE
    }

    fun detachFromParent(view: View?) {
        if (view == null || view.parent == null) {
            return
        }
        val parent = view.parent as? ViewGroup ?: return
        try {
            parent.removeView(view)
        } catch (e: Exception) {
            Logger.throwException(e)
        }

    }

    fun getLocationInAncestor(child: View?, ancestor: View?): IntArray? {
        if (child == null || ancestor == null) {
            Logger.alertErrorInfo("invalid params: child:$child,ancestor:$ancestor")
            return null
        }
        val location = IntArray(2)
        val position = FloatArray(2)
        position[1] = 0.0f
        position[0] = position[1]

        position[0] += child.left.toFloat()
        position[1] += child.top.toFloat()

        var matched = false
        var viewParent = child.parent
        while (viewParent is View) {
            val view = viewParent as View
            if (viewParent === ancestor) {
                matched = true
                break
            }
            position[0] -= view.scrollX.toFloat()
            position[1] -= view.scrollY.toFloat()

            position[0] += view.left.toFloat()
            position[1] += view.top.toFloat()

            viewParent = view.parent
        }
        if (!matched) {
            Logger.alertErrorInfo("ancestorView:$ancestor is not the ancestor of child : $child")
            return null
        }
        location[0] = (position[0] + 0.5f).toInt()
        location[1] = (position[1] + 0.5f).toInt()
        return location
    }

    fun setColorAlpha(color: Int, alpha: Int): Int {
        var alpha = alpha
        if (alpha > 0xff) {
            alpha = 0xff
        } else if (alpha < 0) {
            alpha = 0
        }
        return color and 0xffffff or alpha * 0x1000000
    }

    fun getIndexInParent(view: View?): Int {
        if (view == null || view.parent == null) {
            return -1
        }
        val parent = view.parent
        return (parent as? ViewGroup)?.indexOfChild(view) ?: -1
    }

    fun clearAnimation(view: View?): Boolean {
        if (view == null || view.animation == null) {
            return false
        }
        view.clearAnimation()
        return true
    }
}