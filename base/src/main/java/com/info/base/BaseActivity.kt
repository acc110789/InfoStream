package com.info.base

import android.annotation.SuppressLint
import android.content.MutableContextWrapper
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by zhangxiaolong on 18/3/1.
 */
abstract class BaseActivity : AppCompatActivity(){
    companion object {
        @SuppressLint("StaticFieldLeak")
        val sContext = MutableContextWrapper(BaseApp.inst())
        @SuppressLint("StaticFieldLeak")
        val sViewGroup = object :ViewGroup(sContext){
            override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {}
        }
    }

    private var mTranslucentPair : Pair<ViewGroup , View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val enableTranslucent = enableTranslucent()
        if(enableTranslucent) {
            mTranslucentPair = TranslucentDecor.decorate(this)
            setStatusBarColor(getStatusBarColor())
        }
        super.onCreate(savedInstanceState)

        //获取contentView
        val contentView : View = getContentView() ?: kotlin.run {
            sContext.baseContext = this
            val contentId = getContentViewId()
            when {
                contentId > 0 -> LayoutInflater.from(this).inflate(contentId, sViewGroup , false)
                else -> throw IllegalStateException("you must provider either contentView or contentId")
            }
        }

        //设置contentView
        super.setContentView(mTranslucentPair?.first.apply {
            this?.addView(contentView)
        } ?: contentView)

        bindView()
        bindListener()
        initData(savedInstanceState)
    }

    /**业务方自己负责设置对应的LayoutParams*/
    open fun getContentView(): View? {
        return null
    }

    abstract fun bindView()

    abstract fun bindListener()

    abstract fun initData(savedInstanceState: Bundle?)

    abstract fun getContentViewId(): Int

    override fun setContentView(layoutResID: Int) {}

    override fun setContentView(view: View?) {}

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {}

    //沉浸式状态栏设置相关
    open fun enableTranslucent() = true

    open fun getStatusBarColor() : Int {
        return resources.getColor(R.color.theme_color)
    }

    open fun setStatusBarColor(color : Int) {
        mTranslucentPair?.second?.setBackgroundColor(color)
    }
}
