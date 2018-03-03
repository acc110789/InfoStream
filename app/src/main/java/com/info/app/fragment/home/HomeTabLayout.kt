package com.info.app.fragment.home

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.*
import android.widget.LinearLayout
import android.widget.TextView
import com.info.base.tab.TabLayout
import com.info.zhangxiaolong.myapp.R

/**
 * Created by zhangxiaolong on 18/3/3.
 */
class HomeTabLayout : TabLayout {


    private val mTabSelectedListener = object : OnTabSelectedListener {
        override fun onTabSelected(tab: Tab?) {
            tab?.mView?.isSelected = true
        }

        override fun onTabUnselected(tab: Tab?) {
            tab?.mView?.isSelected = false
        }

        override fun onTabReselected(tab: Tab?) {
            tab?.mView?.isSelected = true
        }

    }

    constructor(context: Context?) : super(context) {
        init(null)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        attrs?.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.HomeTabLayout)
            setBorderWidth(a.getDimension(R.styleable.HomeTabLayout_tabBorderWidth, 0f))
            setBorderColor(a.getColor(R.styleable.HomeTabLayout_tabBorderColor, Color.WHITE))
            a.recycle()
        }
        addOnTabSelectedListener(mTabSelectedListener)
    }

    override fun addTab(tab: Tab, setSelected: Boolean) {
        super.addTab(tab, setSelected)

        val textView = TextView(context)
        textView.id = android.R.id.text1
        textView.layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, MATCH_PARENT)
        textView.gravity = Gravity.CENTER_VERTICAL
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX , resources.getDimension(R.dimen.tab_layout_item_text_size))
        textView.setTextColor(resources.getColorStateList(R.color.nav_title_color))
        textView.maxLines = 1
        textView.isDuplicateParentStateEnabled = true
        textView.background = null
        tab.customView = textView
    }

}