package com.info.zhangxiaolong.myapp.main.fragment.home

import android.support.v4.app.Fragment

/**
 * Created by zhangxiaolong on 18/2/26.
 */
class FragmentProviderImpl : FragmentProvider {
    override fun getZhihuFragment(): Fragment {
        return ZhihuFragment()
    }

    override fun getDoubanFragment(): Fragment {
        return DoubanFragment()
    }

    override fun getGuokrFragment(): Fragment {
        return GuokrFragment()
    }

    override fun getBeautyFragment(): Fragment {
        return BeautyFragment()
    }
}