package com.info.zhangxiaolong.myapp.main.nav;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.info.zhangxiaolong.myapp.R;

/**
 * Created by zhangxiaolong on 2018/2/20.
 */

public class BottomNavView extends LinearLayout {
    private final NavMenu mMenu = new NavMenu();

    public void setOnNavItemReselectedListener(OnNavItemReselectedListener onNavItemReselectedListener) {
        this.onNavItemReselectedListener = onNavItemReselectedListener;
    }

    public void setOnNavItemSelectedListener(OnNavItemSelectedListener onNavItemSelectedListener) {
        this.onNavItemSelectedListener = onNavItemSelectedListener;
    }

    private OnNavItemReselectedListener onNavItemReselectedListener;
    private OnNavItemSelectedListener onNavItemSelectedListener;

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    /**
     * Listener for handling selection events on bottom navigation items.
     */
    public interface OnNavItemSelectedListener {

        /**
         * Called when an item in the bottom navigation menu is selected.
         *
         * @param item The selected item
         * @return true to display the item as the selected item and false if the item should not
         * be selected. Consider setting non-selectable items as disabled preemptively to
         * make them appear non-interactive.
         */
        boolean onNavItemSelected(@NonNull NavMenuItem item);
    }

    /**
     * Listener for handling reselection events on bottom navigation items.
     */
    public interface OnNavItemReselectedListener {

        /**
         * Called when the currently selected item in the bottom navigation menu is selected again.
         *
         * @param item The selected item
         */
        void onNavItemReselected(@NonNull NavMenuItem item);
    }

    public BottomNavView(Context context) {
        super(context);
        init(null);
    }

    public BottomNavView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public BottomNavView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        if (attrs == null) {
            return;
        }

        //obtain menu id
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.BottomNavView);
        int menuId = array.getResourceId(R.styleable.BottomNavView_navMenu, 0);
        array.recycle();

        //obtain navmenu
        NavMenuInflater inflater = new NavMenuInflater(getContext());
        mMenu.clear();
        inflater.inflate(menuId, mMenu);

        for (NavMenuItem item : mMenu.getItems()) {
            BottomNavItemView itemView = new BottomNavItemView(getContext());
            itemView.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.weight = 1;
            itemView.setLayoutParams(lp);
            itemView.bindItem(item);
            addView(itemView);
        }
    }
}
