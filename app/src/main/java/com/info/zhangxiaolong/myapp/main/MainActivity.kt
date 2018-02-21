package com.info.zhangxiaolong.myapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.FrameLayout
import com.info.zhangxiaolong.myapp.R
import com.info.zhangxiaolong.myapp.main.fragment.FavoriteFragment
import com.info.zhangxiaolong.myapp.main.fragment.HomeFragment
import com.info.zhangxiaolong.myapp.main.fragment.SettingsFragment
import com.info.zhangxiaolong.myapp.main.nav.BottomNavView
import com.info.zhangxiaolong.myapp.main.nav.NavMenuItem

class MainActivity : AppCompatActivity() {
    companion object {
        private val KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID = "KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID"
        private val NO_ID = -1

        private val fragmentMap = mutableMapOf<String, String>().apply {
            put(R.id.tab_home.toString(), HomeFragment::class.java.name)
            put(R.id.tab_favorite.toString(), FavoriteFragment::class.java.name)
            put(R.id.tab_settings.toString(), SettingsFragment::class.java.name)
        }
    }

    private var fragmentContainer: FrameLayout? = null
    private var bottomNavView: BottomNavView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragmentContainer = findViewById(R.id.fragment_container)
        bottomNavView = findViewById(R.id.bottom_nav_bar)
        initFragment(savedInstanceState)
        bottomNavView?.setOnNavItemReselectedListener(object : BottomNavView.OnNavItemReselectedListener {
            override fun onNavItemReselected(item: NavMenuItem) {
                //刷新内容
            }

        })
        bottomNavView?.setOnNavItemSelectedListener(object : BottomNavView.OnNavItemSelectedListener {
            override fun onNavItemSelected(item: NavMenuItem) {
                selectFragmentById(item.id)
            }
        })
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        var initSelectedId = savedInstanceState?.getInt(KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID, NO_ID) ?: NO_ID
        if (initSelectedId == NO_ID) {
            initSelectedId = R.id.tab_home
        }
        bottomNavView?.currentSelectedId = initSelectedId
        selectFragmentById(initSelectedId)
    }

    private fun selectFragmentById(id: Int) {
        val tag = id.toString()
        val fragment = supportFragmentManager.findFragmentByTag(tag) ?: fragmentMap[tag]?.let {
            Fragment.instantiate(this@MainActivity, it)
        }
        fragment?.let {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, it, tag).commitAllowingStateLoss()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID, bottomNavView?.currentSelectedId ?: NO_ID)
    }
}
