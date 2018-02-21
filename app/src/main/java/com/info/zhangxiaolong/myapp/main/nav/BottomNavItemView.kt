package com.info.zhangxiaolong.myapp.main.nav

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.info.zhangxiaolong.myapp.R

/**
 * Created by zhangxiaolong on 2018/2/21.
 */

class BottomNavItemView : LinearLayout {
    var mItem: NavMenuItem? = null
    private var mIcon: ImageView? = null
    private var mTitle: TextView? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.nav_menu_item_layout, this)
        mIcon = findViewById(R.id.nav_icon)
        mTitle = findViewById(R.id.nav_title)
    }


    fun bindItem(item: NavMenuItem) {
        this.mItem = item
        mIcon?.setImageResource(item.iconResId)
        mTitle?.text = item.text
    }
}
