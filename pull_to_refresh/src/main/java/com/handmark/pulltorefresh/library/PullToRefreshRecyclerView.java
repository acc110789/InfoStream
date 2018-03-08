package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by zhangxiaolong on 18/3/3.
 */

public class PullToRefreshRecyclerView extends PullToRefreshBase<HeaderAndFooterRecyclerView> {
    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected HeaderAndFooterRecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        HeaderAndFooterRecyclerView result = new HeaderAndFooterRecyclerView(context , attrs);
        RecyclerView.LayoutManager layoutManager = result.getLayoutManager();
        if(layoutManager == null) {
            result.setLayoutManager(new LinearLayoutManager(context , LinearLayoutManager.VERTICAL , false));
        }
        return result;
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return isFirstItemCompletelyVisible();
    }

    protected int getFirstCompletelyVisiblePosition() {
        return mRefreshableView != null ? mRefreshableView.getFirstCompletelyVisiblePosition() : RecyclerView.NO_POSITION;
    }

    /**第一个Item是否是完全可见的*/
    private boolean isFirstItemCompletelyVisible() {
        final RecyclerView.Adapter adapter = mRefreshableView.getAdapter();
        return null == adapter || adapter.getItemCount() <= 0 || getFirstCompletelyVisiblePosition() == 0;
    }
}
