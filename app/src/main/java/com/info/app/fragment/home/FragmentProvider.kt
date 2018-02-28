package com.info.app.fragment.home

import android.support.v4.app.Fragment

/**
 * Created by zhangxiaolong on 18/2/25.
 */
interface FragmentProvider {

    fun getZhihuFragment(): Fragment

    fun getDoubanFragment() : Fragment

    fun getGuokrFragment(): Fragment

    fun getBeautyFragment(): Fragment

}