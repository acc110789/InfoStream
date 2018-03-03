package com.info.app.fragment.home

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.handmark.pulltorefresh.library.PullToRefreshBase
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2
import com.handmark.pulltorefresh.library.PullToRefreshListView
import com.info.zhangxiaolong.myapp.R

/**
 * Created by zhangxiaolong on 18/3/3.
 */
abstract class PullToRefreshListViewFragment<T> : Fragment() {
    private var mPullToRefreshListView : PullToRefreshListView? = null
    private var mListView : ListView? = null
    private var mData = mutableListOf<T>()
    private val mHandler = Handler()

    private val mRefreshListener = object : OnRefreshListener2<ListView> {
        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            mHandler.postDelayed({
                mPullToRefreshListView?.onRefreshComplete()
            }, 3000)
        }

        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {}

    }

    private val mAdapter = object:BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val resultView : View = convertView ?: layoutInflater.inflate(getItemViewLayoutId() , parent , false)
            val tag = resultView.tag ?: onCreateViewHolder(resultView)
            val viewHolder = tag as ViewHolder
            resultView.tag = viewHolder
            onBindViewHolderWithData(viewHolder , getItem(position))
            return resultView
        }

        override fun getItem(position: Int): T {
            return mData[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mData.size
        }
    }

    abstract fun onCreateViewHolder(view : View) : ViewHolder

    abstract fun onBindViewHolderWithData(viewHolder : ViewHolder, data : T)

    abstract fun getItemViewLayoutId():Int

    fun addData(datas : List<T>) {
        mData.addAll(datas)
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPullToRefreshListView = inflater.inflate(R.layout.pull_to_refresh_list_view_layout ,
                container , false) as PullToRefreshListView
        mPullToRefreshListView?.setOnRefreshListener(mRefreshListener)
        mListView = mPullToRefreshListView?.refreshableView
        mListView?.adapter = mAdapter
        return mPullToRefreshListView
    }
}