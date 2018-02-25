package com.info.zhangxiaolong.myapp.main.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.info.zhangxiaolong.myapp.R
import com.info.zhangxiaolong.myapp.main.fragment.home.FragmentProviderImpl
import com.info.zhangxiaolong.myapp.main.fragment.home.PageAdaper

/**
 * Created by zhangxiaolong on 2018/2/21.
 */
class HomeFragment : Fragment() {

    private var tabNameContainer : TabLayout? = null
    private var tabContentContainer : ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_page_layout , container , false)
        tabNameContainer = view.findViewById(R.id.tab_name_container)
        tabContentContainer = view.findViewById(R.id.tab_content_container)

        tabContentContainer?.adapter = PageAdaper(childFragmentManager , FragmentProviderImpl())
        tabNameContainer?.setupWithViewPager(tabContentContainer)

        return view
    }
}