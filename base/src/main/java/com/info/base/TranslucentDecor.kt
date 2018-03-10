package com.info.base

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout

/**
 * Created by zhangxiaolong on 18/3/1.
 */
object TranslucentDecor {

    /**
     * @return Pair<ViewGroup,View>
     *     pair的first返回一个contentParent容器
     *     pair的second是fakeStatusBar
     * */
    fun decorate(activity: Activity) : Pair<ViewGroup,View> {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        val parent = LinearLayout(activity)
        parent.orientation = LinearLayout.VERTICAL

        val statusBar = View(activity)
        statusBar.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.getStatusBarHeight())
        parent.addView(statusBar)
        parent.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        return parent.to(statusBar)
    }
}