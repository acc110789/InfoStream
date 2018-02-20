package com.info.zhangxiaolong.myapp.main.nav;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiaolong on 2018/2/20.
 */

public class NavMenu {
    private final List<NavMenuItem> items = new ArrayList<>();

    public void addMenuItem(NavMenuItem item) {
        if(item != null) {
            items.add(item);
        }
    }

    public void clear() {
        items.clear();
    }

    public List<NavMenuItem> getItems() {
        return new ArrayList<>(items);
    }
}
