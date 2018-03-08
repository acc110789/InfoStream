package com.info.app.fragment.home

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.handmark.pulltorefresh.library.PullToRefreshBase
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView
import com.info.zhangxiaolong.myapp.R
import java.util.*

/**
 * Created by zhangxiaolong on 18/3/3.
 */

abstract class PullToRefreshRecyclerViewFragment<T> : Fragment() {
    private var mPullToRecyclerView : PullToRefreshRecyclerView? = null
    private var mRecyclerView : RecyclerView? = null
    private val mListItems = LinkedList<T>()
    private val mHandler = Handler()

    private val mRefreshListener2 = object : PullToRefreshBase.OnRefreshListener2<ListView> {
        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            mHandler.postDelayed({
                mPullToRecyclerView?.onRefreshComplete()
            }, 3000)
        }

        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {}

    }

    private val mAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            onBindViewHolderWithData(holder , getItem(position))
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return this@PullToRefreshRecyclerViewFragment.onCreateViewHolder(
                    LayoutInflater.from(context).inflate(getItemViewLayoutId() , parent , false))
        }

        override fun getItemCount(): Int {
            return mListItems.size
        }

        private fun getItem(position: Int): T {
            return mListItems[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
    }

    abstract fun onCreateViewHolder(view: View): RecyclerView.ViewHolder

    abstract fun onBindViewHolderWithData(viewHolder: RecyclerView.ViewHolder, data: T)

    abstract fun getItemViewLayoutId(): Int

    fun addData(data: List<T>) {
        mListItems.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPullToRecyclerView = inflater.inflate(R.layout.pull_to_refresh_recycler_view_layout ,
                container , false) as PullToRefreshRecyclerView?
        mRecyclerView = mPullToRecyclerView?.refreshableView
        mRecyclerView?.adapter = mAdapter
        return mPullToRecyclerView
    }
}
