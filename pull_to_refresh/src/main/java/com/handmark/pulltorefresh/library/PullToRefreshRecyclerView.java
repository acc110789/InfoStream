package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by zhangxiaolong on 18/3/3.
 */

public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
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
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        return new RecyclerView(context, attrs);
    }

    @Override
    protected boolean isReadyForPullEnd() {
        return isLastItemVisible();
    }

    private boolean isLastItemVisible() {
        RecyclerView recyclerView = getRefreshableView();
        if (recyclerView == null) {
            throw new IllegalStateException("no recyclerView available");
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager == null) {
            throw new IllegalStateException("layout manager is not set");
        }
        //总数量
        int itemCount = recyclerView.getAdapter().getItemCount();
        //可见数量
        int visibleCount = layoutManager.getChildCount();
        int state = recyclerView.getScrollState();
        int lastVisibleItemPosition = RecyclerView.NO_POSITION;

        if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
            //屏幕中最后一个可见子项的position
            int[] positions = manager.findLastVisibleItemPositions(null);
            for (int tmp : positions) {
                lastVisibleItemPosition = Math.max(tmp, lastVisibleItemPosition);
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        } else {
            throw new IllegalStateException("unsupported layout manager");
        }
        return visibleCount > 0 && lastVisibleItemPosition >= itemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE;
    }

    @Override
    protected boolean isReadyForPullStart() {
        return isFirstItemVisible();
    }

    private boolean isFirstItemVisible() {
        return false;
    }
}
