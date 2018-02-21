package com.info.zhangxiaolong.myapp.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.info.zhangxiaolong.myapp.R

/**
 * Created by zhangxiaolong on 2018/2/21.
 */
class FavoriteFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.favority_page_layout , container , false)
    }
}