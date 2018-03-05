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
import android.text.format.DateUtils
import android.os.AsyncTask
import android.widget.Toast
import java.util.*


/**
 * Created by zhangxiaolong on 18/3/3.
 */
abstract class PullToRefreshListViewFragment<T> : Fragment() {
    private var mPullToRefreshListView : PullToRefreshListView? = null
    private var mListView: ListView? = null
    private val mListItems = LinkedList<T>()
    private val mHandler = Handler()

    private val mRefreshListener2 = object : OnRefreshListener2<ListView> {
        override fun onPullDownToRefresh(refreshView: PullToRefreshBase<ListView>?) {
            mHandler.postDelayed({
                mPullToRefreshListView?.onRefreshComplete()
            }, 3000)
        }

        override fun onPullUpToRefresh(refreshView: PullToRefreshBase<ListView>?) {}

    }

    private val mRefreshListener1 = PullToRefreshBase.OnRefreshListener<ListView> {
        val label = DateUtils.formatDateTime(context?.applicationContext, System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_DATE or DateUtils.FORMAT_ABBREV_ALL)
    }

    private val mAdapter = object : BaseAdapter() {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val resultView: View = convertView ?: layoutInflater.inflate(getItemViewLayoutId(), parent, false)
            val tag = resultView.tag ?: onCreateViewHolder(resultView)
            val viewHolder = tag as ViewHolder
            resultView.tag = viewHolder
            onBindViewHolderWithData(viewHolder, getItem(position))
            return resultView
        }

        override fun getItem(position: Int): T {
            return mListItems[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mListItems.size
        }
    }

    abstract fun onCreateViewHolder(view: View): ViewHolder

    abstract fun onBindViewHolderWithData(viewHolder: ViewHolder, data: T)

    abstract fun getItemViewLayoutId(): Int

    fun addData(data: List<T>) {
        mListItems.addAll(data)
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mPullToRefreshListView = inflater.inflate(R.layout.pull_to_refresh_list_view_layout,
                container, false) as PullToRefreshListView
        mPullToRefreshListView?.setOnRefreshListener(mRefreshListener2)
        mListView = mPullToRefreshListView?.refreshableView
        mListView?.adapter = mAdapter
        return mPullToRefreshListView
    }
}

