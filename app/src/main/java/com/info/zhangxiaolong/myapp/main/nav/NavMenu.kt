package com.info.zhangxiaolong.myapp.main.nav


import java.util.ArrayList

/**
 * Created by zhangxiaolong on 2018/2/20.
 */

class NavMenu {
    private val items = ArrayList<NavMenuItem>()

    fun addMenuItem(item: NavMenuItem?) {
        if (item != null) {
            items.add(item)
        }
    }

    fun clear() {
        items.clear()
    }

    fun getItems(): List<NavMenuItem> {
        return ArrayList(items)
    }
}
