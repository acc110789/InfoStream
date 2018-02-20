package com.info.zhangxiaolong.myapp.main.nav;

/**
 * Created by zhangxiaolong on 2018/2/20.
 */

public class NavMenuItem {
    public final int id;

    public NavMenuItem(int id, CharSequence text, int iconResId) {
        this.id = id;
        this.text = text;
        this.iconResId = iconResId;
    }

    public int getId() {
        return id;
    }

    public CharSequence getText() {
        return text;
    }

    public int getIconResId() {
        return iconResId;
    }

    public final CharSequence text;
    private final int iconResId;
}
