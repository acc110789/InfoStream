package com.info.app.fragment.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView
import com.info.zhangxiaolong.myapp.R

/**
 * Created by zhangxiaolong on 18/3/3.
 */

open class PullToRefreshRecyclerViewFragment : Fragment() {
    private var mPullToRecyclerView : PullToRefreshRecyclerView? = null
    private var mRecyclerView : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPullToRecyclerView = inflater.inflate(R.layout.pull_to_refresh_recycler_view_layout ,
                container , false) as PullToRefreshRecyclerView?
        mRecyclerView = mPullToRecyclerView?.refreshableView
        return mPullToRecyclerView
    }
}
