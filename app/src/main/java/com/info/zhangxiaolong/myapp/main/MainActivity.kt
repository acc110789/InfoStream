package com.info.zhangxiaolong.myapp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
    }

    private var fragmentContainer : FrameLayout? = null
    private var bottomNavView : BottomNavView? = null

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
                val fragment =  when(item.id) {
                    R.id.tab_home -> getHomeFragment(R.id.tab_home)
                    R.id.tab_favorite -> getFavorityFragment(R.id.tab_favorite)
                    R.id.tab_settings -> getSettingsFragment(R.id.tab_settings)
                    else -> throw IllegalStateException("id is known")
                }
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container , fragment).commitAllowingStateLoss()
            }

        })
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        var initSelectedId = savedInstanceState?.getInt(KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID, NO_ID) ?: NO_ID
        if(initSelectedId == NO_ID) {
            initSelectedId = R.id.tab_home
        }
        bottomNavView?.currentSelectedId = initSelectedId

        var fragment = when(initSelectedId) {
            R.id.tab_home -> getHomeFragment(savedInstanceState)
            R.id.tab_favorite -> getFavorityFragment(savedInstanceState)
            R.id.tab_settings -> getSettingsFragment(savedInstanceState)
            else -> throw IllegalStateException("id is known")
        }
        supportFragmentManager.beginTransaction().
                replace(R.id.fragment_container , fragment , fragment.javaClass.simpleName).
                commitAllowingStateLoss()
    }

    private fun getHomeFragment(savedInstanceState: Bundle?) : HomeFragment {
        return supportFragmentManager.getFragment(savedInstanceState ,
                HomeFragment::class.simpleName) as HomeFragment? ?: HomeFragment()
    }

    private fun getHomeFragment(id: Int) : HomeFragment {
        return supportFragmentManager.findFragmentByTag(id.toString()) as HomeFragment? ?: HomeFragment()
    }

    private fun getFavorityFragment(savedInstanceState: Bundle?) : FavoriteFragment {
        return supportFragmentManager.getFragment(savedInstanceState ,
                FavoriteFragment::class.simpleName) as FavoriteFragment? ?: FavoriteFragment()
    }

    private fun getFavorityFragment(id : Int) : FavoriteFragment {
        return supportFragmentManager.findFragmentByTag(id.toString()) as FavoriteFragment? ?: FavoriteFragment()
    }

    private fun getSettingsFragment(savedInstanceState: Bundle?) : SettingsFragment {
        return supportFragmentManager.getFragment(savedInstanceState ,
                SettingsFragment::class.simpleName) as SettingsFragment? ?: SettingsFragment()
    }

    private fun getSettingsFragment(id: Int) : SettingsFragment {
        return supportFragmentManager.findFragmentByTag(id.toString()) as SettingsFragment? ?: SettingsFragment()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(KEY_BOTTOM_NAVIGATION_VIEW_SELECTED_ID , bottomNavView?.currentSelectedId?: NO_ID)
    }
}
