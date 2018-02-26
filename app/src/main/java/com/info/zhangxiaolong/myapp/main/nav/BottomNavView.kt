package com.info.zhangxiaolong.myapp.main.nav

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.info.zhangxiaolong.myapp.R

/**
 * Created by zhangxiaolong on 2018/2/20.
 */

class BottomNavView : LinearLayout {
    private val mMenu = NavMenu()

    private var onNavItemReselectedListener: OnNavItemReselectedListener? = null
    private var onNavItemSelectedListener: OnNavItemSelectedListener? = null

    private val mOnClickListener = OnClickListener {
        (it as BottomNavItemView).mItem?.let {
            if(it.id == currentSelectedId) {
                onNavItemReselectedListener?.onNavItemReselected(it)
            } else{
                currentSelectedId = it.id
                onNavItemSelectedListener?.onNavItemSelected(it)
            }
        }
        select(it)
    }

    private fun select(toBeSelect : View) {
        (0 until childCount)
                .map { getChildAt(it) }
                .forEach { it.isSelected = (it == toBeSelect) }
    }

    var currentSelectedId : Int = View.NO_ID
    set(value) = (0 until childCount)
            .map { getChildAt(it) }
            .forEach { val itemView = it as BottomNavItemView
                if(itemView.mItem?.id == value) {
                    select(itemView)
                    return@forEach
                }
            }

    fun setOnNavItemReselectedListener(onNavItemReselectedListener: OnNavItemReselectedListener?) {
        this.onNavItemReselectedListener = onNavItemReselectedListener
    }

    fun setOnNavItemSelectedListener(onNavItemSelectedListener: OnNavItemSelectedListener?) {
        this.onNavItemSelectedListener = onNavItemSelectedListener
    }

    /**
     * Listener for handling selection events on bottom navigation items.
     */
    interface OnNavItemSelectedListener {

        /**
         * Called when an item in the bottom navigation menu is selected.
         *
         * @param item The selected item
         * @return true to display the item as the selected item and false if the item should not
         * be selected. Consider setting non-selectable items as disabled preemptively to
         * make them appear non-interactive.
         */
        fun onNavItemSelected(item: NavMenuItem)
    }

    /**
     * Listener for handling reselection events on bottom navigation items.
     */
    interface OnNavItemReselectedListener {

        /**
         * Called when the currently selected item in the bottom navigation menu is selected again.
         *
         * @param item The selected item
         */
        fun onNavItemReselected(item: NavMenuItem)
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }

        //obtain menu id
        val array = context.obtainStyledAttributes(attrs, R.styleable.BottomNavView)
        val menuId = array.getResourceId(R.styleable.BottomNavView_navMenu, 0)
        array.recycle()

        //obtain navmenu
        val inflater = NavMenuInflater(context)
        mMenu.clear()
        inflater.inflate(menuId, mMenu)

        for (item in mMenu.getItems()) {
            val itemView = BottomNavItemView(context)
            itemView.orientation = LinearLayout.VERTICAL
            val lp = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
            lp.weight = 1f
            itemView.layoutParams = lp
            itemView.bindItem(item)
            itemView.setOnClickListener(mOnClickListener)
            addView(itemView)
        }
    }
}
