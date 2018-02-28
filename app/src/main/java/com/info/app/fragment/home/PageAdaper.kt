package com.info.app.fragment.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.info.zhangxiaolong.myapp.R
import com.info.base.BaseApp

/**
 * Created by zhangxiaolong on 18/2/25.
 */
class PageAdaper(fm: FragmentManager?, private val fragmentProvider: FragmentProvider) : FragmentStatePagerAdapter(fm) {
    private val channelCount = 4
    private val zhihuIndex = 0
    private val doubanIndex = 1
    private val guokrIndex = 2
    private val beautyIndex = 3

    private val cache = List(channelCount , {
        when(it) {
            zhihuIndex -> fragmentProvider.getZhihuFragment()
            doubanIndex -> fragmentProvider.getDoubanFragment()
            guokrIndex -> fragmentProvider.getGuokrFragment()
            beautyIndex -> fragmentProvider.getBeautyFragment()
            else -> throw IllegalArgumentException("params is wrong!")
        }
    })

    override fun getItem(position: Int): Fragment {
        return cache[position]
    }

    override fun getCount(): Int {
        return channelCount
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val redId = when(position) {
            zhihuIndex -> R.string.channel_zhihu
            doubanIndex -> R.string.channel_douban
            guokrIndex -> R.string.channel_guoke
            beautyIndex -> R.string.channel_beauty
            else -> throw IllegalArgumentException("params is wrong!")
        }
        return BaseApp.inst().getString(redId)
    }
}