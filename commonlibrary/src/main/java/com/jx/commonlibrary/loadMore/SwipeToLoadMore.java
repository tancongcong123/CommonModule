package com.jx.commonlibrary.loadMore;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class SwipeToLoadMore extends RecyclerView.OnScrollListener {

    public static final int STATUS_LOADING=0;
    public static final int STATUS_LOADING_ERROR=1;
    public static final int STATUS_LOADING_DONE=2;
    public static final int STATUS_LOADING_GONE=3;
    private int currentStatus;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdapterWrapper mAdapterWrapper;
    private LoadMoreListener mListener;
    /** 是否正在加载中 */
    private boolean mLoading = false;
    /** 上拉刷新功能是否开启 */
    private boolean mIsSwipeToLoadEnabled = true;

    public SwipeToLoadMore(RecyclerView recyclerView, AdapterWrapper adapterWrapper) {
        mLayoutManager = recyclerView.getLayoutManager();
        mAdapterWrapper = adapterWrapper;
        mRecyclerView = recyclerView;

        if (mLayoutManager instanceof LinearLayoutManager) {
            mAdapterWrapper.setAdapterType(AdapterWrapper.ADAPTER_TYPE_LINEAR);
        }

        // 将OnScrollListener设置RecyclerView
        mRecyclerView.addOnScrollListener(this);
    }

    private boolean isCanLoad(){
        return currentStatus != STATUS_LOADING_DONE;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (mIsSwipeToLoadEnabled && SCROLL_STATE_IDLE == newState && !mLoading) {
            if (mLayoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mLayoutManager;
                int lastCompletePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                // only when the complete visible item is second last
                if (lastCompletePosition == mLayoutManager.getItemCount() - 2) {
                    int firstCompletePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    View child = linearLayoutManager.findViewByPosition(lastCompletePosition);
                    if (child == null)
                        return;
                    int deltaY = recyclerView.getBottom() - recyclerView.getPaddingBottom() - child.getBottom();
                    if (deltaY > 0 && firstCompletePosition != 0) {
                        recyclerView.scrollBy(0, -deltaY);
                    }
                } else if (lastCompletePosition == mLayoutManager.getItemCount() - 1 && isCanLoad() && mLayoutManager.getItemCount()>1) {
                    // 最后一项完全显示, 触发操作, 执行加载更多操作 禁用回弹判断
                    mLoading = true;
                    mAdapterWrapper.setLoadItemState(true, STATUS_LOADING);
                    if (mListener != null) {
                        mListener.onLoadMore();
                    }
                }
            }
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    /** 设置下拉刷新功能是否开启 */
    public void setSwipeToLoadEnabled(boolean isSwipeToLoadEnabled) {
        if (mIsSwipeToLoadEnabled != isSwipeToLoadEnabled) {
            mIsSwipeToLoadEnabled = isSwipeToLoadEnabled;
            mAdapterWrapper.setLoadItemVisibility(isSwipeToLoadEnabled);
        }
    }

    /** 设置LoadMore Item为加载完成状态, 上拉加载更多完成时调用 */
    public void setLoadMoreStatus(int status) {
        currentStatus = status;
        mLoading = false;
        mAdapterWrapper.setLoadItemState(false, status);
    }

    /** 上拉操作触发时调用的接口 */
    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        mListener = loadMoreListener;
    }

    public interface LoadMoreListener {
        void onLoadMore();
    }
}
