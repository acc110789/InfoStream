package com.info.app.fragment.home

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.info.zhangxiaolong.myapp.R


/**
 * Created by zhangxiaolong on 18/2/26.
 */
class ZhihuFragment : PullToRefreshRecyclerViewFragment<String>() {

    override fun onCreateViewHolder(view: View): RecyclerView.ViewHolder {
        return ZhihuViewHolder(view)
    }

    override fun onBindViewHolderWithData(viewHolder: RecyclerView.ViewHolder, data: String) {
        if(viewHolder is ZhihuViewHolder) {
            viewHolder.setContent(data)
        }
    }

    override fun getItemViewLayoutId(): Int {
        return R.layout.list_view_item_layout
    }

    private var isFirstResume = true

    override fun onResume() {
        super.onResume()
        if(isFirstResume) {
            isFirstResume = false
            val data = ArrayList<String>().apply {
                repeat(18,{
                    add("News" + it)
                })
            }
            addData(data)
        }
    }
}

class ZhihuViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    private val mTextView : TextView = itemView.findViewById(R.id.text_content)

    fun setContent(data : String) {
        mTextView.text = data
    }
}