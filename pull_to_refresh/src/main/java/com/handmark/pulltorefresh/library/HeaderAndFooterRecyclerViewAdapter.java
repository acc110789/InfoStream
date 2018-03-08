package com.handmark.pulltorefresh.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zhangxiaolong on 18/3/9.
 */

class HeaderAndFooterRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER_VIEW = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER_VIEW = Integer.MIN_VALUE + 1;

    /**
     * RecyclerView使用的，真正的Adapter
     */
    private RecyclerView.Adapter<T> mInnerAdapter;

    private final List<View> mHeaderViews;
    private final List<View> mFooterViews;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {

        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            notifyItemRangeChanged(positionStart + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            notifyItemRangeInserted(positionStart + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            notifyItemRangeRemoved(positionStart + getHeaderViewsCount(), itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            int headerViewsCountCount = getHeaderViewsCount();
            notifyItemRangeChanged(fromPosition + headerViewsCountCount, toPosition + headerViewsCountCount + itemCount);
        }
    };

    HeaderAndFooterRecyclerViewAdapter(RecyclerView.Adapter<T> innerAdapter,
                                       @NonNull List<View> headers, @NonNull List<View> footers) {
        this.mHeaderViews = headers;
        this.mFooterViews = footers;
        setAdapter(innerAdapter);
    }

    private int getHeaderViewsCount() {
        return mHeaderViews.size();
    }

    private int getFooterViewsCount() {
        return mFooterViews.size();
    }

    RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    /**
     * 设置adapter
     * @param adapter
     */
    private void setAdapter(RecyclerView.Adapter<T> adapter) {
        if(adapter != null) {
            if (mInnerAdapter != null) {
                notifyItemRangeRemoved(getHeaderViewsCount(), mInnerAdapter.getItemCount());
                mInnerAdapter.unregisterAdapterDataObserver(mDataObserver);
            }
            this.mInnerAdapter = adapter;
            mInnerAdapter.registerAdapterDataObserver(mDataObserver);
            notifyItemRangeInserted(getHeaderViewsCount(), mInnerAdapter.getItemCount());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int headerViewsCount = getHeaderViewsCount();
        if (viewType < TYPE_HEADER_VIEW + headerViewsCount) {
            return new ViewHolder(mHeaderViews.get(viewType - TYPE_HEADER_VIEW));
        } else if (viewType >= TYPE_FOOTER_VIEW && viewType < Integer.MAX_VALUE / 2) {
            return new ViewHolder(mFooterViews.get(viewType - TYPE_FOOTER_VIEW));
        } else {
            return mInnerAdapter.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int headerViewsCountCount = getHeaderViewsCount();
        if (position >= headerViewsCountCount && position < headerViewsCountCount + mInnerAdapter.getItemCount()) {
            //noinspection unchecked
            mInnerAdapter.onBindViewHolder((T)holder, position - headerViewsCountCount);
        } else {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if(layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            } else if (layoutParams == null && mLayoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager.LayoutParams lp = new StaggeredGridLayoutManager.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
                lp.setFullSpan(true);
                holder.itemView.setLayoutParams(lp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return getHeaderViewsCount() + getFooterViewsCount() + mInnerAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        int innerCount = mInnerAdapter.getItemCount();
        int headerViewsCountCount = getHeaderViewsCount();
        if (position < headerViewsCountCount) {
            return TYPE_HEADER_VIEW + position;
        } else if (headerViewsCountCount <= position && position < headerViewsCountCount + innerCount) {
            int innerItemViewType = mInnerAdapter.getItemViewType(position - headerViewsCountCount);
            if(innerItemViewType >= Integer.MAX_VALUE / 2) {
                throw new IllegalArgumentException("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
            }
            return innerItemViewType + Integer.MAX_VALUE / 2;
        } else {
            return TYPE_FOOTER_VIEW + position - headerViewsCountCount - innerCount;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);

        if (mLayoutManager == null) {
            mLayoutManager = recyclerView.getLayoutManager();
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        if (mLayoutManager == null && mRecyclerView != null) {
            mLayoutManager = mRecyclerView.getLayoutManager();
        }
    }
}
