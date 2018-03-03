package com.info.app.fragment.home

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.design.widget.TabLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.*
import android.widget.LinearLayout
import android.widget.TextView
import com.info.zhangxiaolong.myapp.R

/**
 * Created by zhangxiaolong on 18/3/3.
 */
class HomeTabLayout : TabLayout {

    private var mBorderWidth : Float = 0f
    private var mBorderPaint = Paint()

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
            val typeArray =  context.obtainStyledAttributes(it , R.styleable.HomeTabLayout)
            mBorderWidth = Math.max(0f,typeArray.getDimension(R.styleable.HomeTabLayout_tabBorderWidth,0f))
            mBorderPaint.color = typeArray.getColor(R.styleable.HomeTabLayout_tabBorderColor , Color.WHITE)
            typeArray.recycle()
        }
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


    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        val borderWidth = mBorderWidth
        if(borderWidth > 0) {
            val heightFloat  = height.toFloat()
            canvas?.drawRect(0f, heightFloat - borderWidth , width.toFloat(),  heightFloat , mBorderPaint)
        }
    }

}