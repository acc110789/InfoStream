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
        // Update the LastUpdatedLabel
        it.loadingLayoutProxy.setLastUpdatedLabel(label)
        // Do work to refresh the list here.
        GetDataTask().execute()
    }

    private val mLastItemItemVisibleListener = PullToRefreshBase.OnLastItemVisibleListener {
        Toast.makeText(activity, "End of List!", Toast.LENGTH_SHORT).show()
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
        mPullToRefreshListView?.setOnRefreshListener(mRefreshListener1)
        mPullToRefreshListView?.setOnLastItemVisibleListener(mLastItemItemVisibleListener)
        mListView = mPullToRefreshListView?.refreshableView
        mListView?.adapter = mAdapter
        return mPullToRefreshListView
    }

    abstract fun getOneData() : T

    private inner class GetDataTask : AsyncTask<Void, Void, Array<String>>() {

        private val mStrings = arrayOf("Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler")

        override fun doInBackground(vararg params: Void): Array<String> {
            // Simulates a background job.
            try {
                Thread.sleep(4000)
            } catch (e: InterruptedException) {
            }

            return mStrings
        }

        override fun onPostExecute(result: Array<String>) {
            mListItems.addFirst(getOneData())
            mAdapter.notifyDataSetChanged()

            // Call onRefreshComplete when the list has been refreshed.
            mPullToRefreshListView?.onRefreshComplete()

            super.onPostExecute(result)
        }
    }

}

