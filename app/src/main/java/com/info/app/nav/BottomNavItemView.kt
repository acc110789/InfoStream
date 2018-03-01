package com.info.app.nav

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.info.zhangxiaolong.myapp.R
import com.info.app.InfoApp
import com.info.base.BaseApp

/**
 * Created by zhangxiaolong on 2018/2/21.
 */

class BottomNavItemView : LinearLayout {
    var mItem: NavMenuItem? = null
    private var mIcon: ImageView? = null
    private var mTitle: TextView? = null

    companion object {
        val selectedColorFilter = PorterDuffColorFilter(
                BaseApp.inst().resources.getColor(R.color.theme_color) , PorterDuff.Mode.SRC_IN)
    }

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

    override fun dispatchSetSelected(selected: Boolean) {
        super.dispatchSetSelected(selected)
        resetColorFilter()
    }

    override fun dispatchSetPressed(pressed: Boolean) {
        super.dispatchSetPressed(pressed)
        resetColorFilter()
    }

    private fun resetColorFilter() {
        mIcon?.colorFilter = if(isSelected || isPressed) selectedColorFilter else null
    }

    fun bindItem(item: NavMenuItem) {
        this.mItem = item
        mIcon?.setImageResource(item.iconResId)
        mTitle?.text = item.text
    }
}
