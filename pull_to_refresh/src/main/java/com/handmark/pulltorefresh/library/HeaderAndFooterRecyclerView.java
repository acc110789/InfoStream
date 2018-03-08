package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by zhangxiaolong on 18/3/9.
 */

public class HeaderAndFooterRecyclerView extends RecyclerView {
    private HeaderAndFooterRecyclerViewAdapter mAdapter;
    private final ArrayList<View> mHeaderViews = new ArrayList<>();
    private final ArrayList<View> mFooterViews = new ArrayList<>();

    public HeaderAndFooterRecyclerView(Context context) {
        super(context);
    }

    public HeaderAndFooterRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderAndFooterRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void superSetAdapter(@NonNull HeaderAndFooterRecyclerViewAdapter adapter) {
        mAdapter = adapter;
        super.setAdapter(adapter);
    }

    @Override
    public  void  setAdapter(Adapter adapter) {
        if(adapter != null) {
            if(adapter instanceof HeaderAndFooterRecyclerViewAdapter) {
                superSetAdapter((HeaderAndFooterRecyclerViewAdapter) adapter);
            } else {
                //noinspection unchecked
                superSetAdapter(new HeaderAndFooterRecyclerViewAdapter(adapter , mHeaderViews , mFooterViews));
            }
        }
    }

    public Adapter getInnerAdapter() {
        if(mAdapter != null) {
            return mAdapter.getInnerAdapter();
        }
        return null;
    }

    private void notifyDataSetChanged() {
        Adapter adapter = getAdapter();
        if(adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public void addHeaderView(View header) {
        if (header != null) {
            mHeaderViews.add(header);
            notifyDataSetChanged();
        }
    }

    public void addFooterView(View footer) {
        if(footer != null) {
            mFooterViews.add(footer);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderView(View view) {
        mHeaderViews.remove(view);
        notifyDataSetChanged();
    }

    public void removeFooterView(View view) {
        mFooterViews.remove(view);
        notifyDataSetChanged();
    }

    public int getHeaderViewsCount() {
        return mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return mFooterViews.size();
    }

    public int getFirstVisiblePosition() {
        if (getChildCount() > 0) {
            return getChildAdapterPosition(getChildAt(0));
        } else {
            return NO_POSITION;
        }
    }

    /**完全可见的位置中，返回最小的那个位置*/
    public int getFirstCompletelyVisiblePosition() {
        LayoutManager layoutManager = getLayoutManager();
        if(layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            return linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        } else if(layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            int[] candidates = staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(null);
            int result = NO_POSITION;
            for(int candidate : candidates) {
                if(candidate != NO_POSITION) {
                    if(result == NO_POSITION) {
                        result = candidate;
                    } else {
                        result = Math.min(result , candidate);
                    }
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("unsupported layout manager : " + layoutManager.getClass().getName());
        }
    }
}
