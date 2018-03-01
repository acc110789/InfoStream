package com.info.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout

/**
 * 支持沉浸式状态栏
 * Created by zhangxiaolong on 18/3/1.
 */
abstract class TranslucentActivity : BaseActivity() {

    private var mFakeStatusBar: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //添加沉浸式状态栏的效果
        val enableTranslucent = enableTranslucent()
        if (enableTranslucent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        super.onCreate(savedInstanceState)

        var contentView: View = getContentView() ?: kotlin.run {
            val contentId = getContentViewId()
            when {
                contentId > 0 -> LayoutInflater.from(this).inflate(contentId, null)
                else -> throw IllegalStateException("you must provider either contentView or contentId")
            }
        }
        if (enableTranslucent) {
            val contentParentView = getContentParentWithStatusBar()
            contentParentView.addView(contentView)
            contentView = contentParentView
        }
        super.setContentView(contentView)
    }

    override fun setContentView(layoutResID: Int) {}

    override fun setContentView(view: View?) {}

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {}

    private fun getContentParentWithStatusBar(): ViewGroup {
        val parent = LinearLayout(this)
        parent.orientation = LinearLayout.VERTICAL

        mFakeStatusBar?.let {
            //已经有mFakeStatusBar了
            val oldParent = it.parent
            if(oldParent is ViewGroup) {
                oldParent.removeView(it)
            }
            parent.addView(it)
        } ?: kotlin.run {
            val statusBar = View(this)
            statusBar.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.getStatusBarHeight(this))
            parent.addView(statusBar)
            mFakeStatusBar = statusBar
            setStatusBarColor(getStatusBarColor())
        }
        return parent
    }

    open fun enableTranslucent() = true

    open fun getContentView(): View? {
        return null
    }

    abstract fun getContentViewId(): Int

    open fun getStatusBarColor() : Int {
        return resources.getColor(R.color.theme_color)
    }

    open fun setStatusBarColor(color : Int) {
        mFakeStatusBar?.setBackgroundColor(color)
    }
}