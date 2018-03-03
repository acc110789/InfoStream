package com.info.app.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.info.zhangxiaolong.myapp.R
import com.info.app.fragment.home.FragmentProviderImpl
import com.info.app.fragment.home.HomeTabLayout
import com.info.app.fragment.home.PageAdaper

/**
 * Created by zhangxiaolong on 2018/2/21.
 */
class HomeFragment : Fragment() {

    private var tabNameContainer : HomeTabLayout? = null
    private var tabContentContainer : ViewPager? = null
    private var toolBar : Toolbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_page_layout , container , false)
        toolBar = view.findViewById(R.id.tool_bar)
        tabNameContainer = view.findViewById(R.id.tab_name_container)
        tabContentContainer = view.findViewById(R.id.tab_content_container)

        tabContentContainer?.adapter = PageAdaper(childFragmentManager, FragmentProviderImpl())
        tabNameContainer?.setupWithViewPager(tabContentContainer)

        return view
    }
}