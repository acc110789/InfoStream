package com.info.zhangxiaolong.myapp.main.nav;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.info.zhangxiaolong.myapp.R;

/**
 * Created by zhangxiaolong on 2018/2/21.
 */

public class BottomNavItemView extends LinearLayout {
    private NavMenuItem mItem;
    private ImageView mIcon;
    private TextView mTitle;
    public BottomNavItemView(Context context) {
        super(context);
        init();
    }

    public BottomNavItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomNavItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.nav_menu_item_layout, this);
        mIcon = findViewById(R.id.nav_icon);
        mTitle = findViewById(R.id.nav_title);
    }


    public void bindItem(NavMenuItem item) {
        this.mItem = item;
        mIcon.setImageResource(item.getIconResId());
        mTitle.setText(item.getText());
    }
}
