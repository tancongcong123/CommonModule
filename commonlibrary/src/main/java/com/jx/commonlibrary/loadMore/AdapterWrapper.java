package com.jx.commonlibrary.loadMore;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jx.commonlibrary.R;

import butterknife.ButterKnife;

public class AdapterWrapper extends RecyclerView.Adapter {

    /** 线性 */
    public static final int ADAPTER_TYPE_LINEAR = 0x01;
    /** view type : "上拉加载更多" */
    private static final int ITEM_TYPE_LOAD = Integer.MAX_VALUE / 2;

    private View defaultView;
    private TextView tvDefaultLoad;
    private ProgressBar pbDefault;

    private RecyclerView.Adapter mAdapter;

    private boolean mShowLoadItem = true;

    private WrapperHolder mWrapperHolder;

    private int mAdapterType = ADAPTER_TYPE_LINEAR;

    public AdapterWrapper(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    /** 设置Wrapper的类型, 默认是线性的 */
    public void setAdapterType(int type) {
        if (mAdapterType != type) {
            mAdapterType = type;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_LOAD) {
            if (mWrapperHolder == null) {
                defaultView = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_load_more, null);
                //mWrapperHolder = new WrapperHolder(View.inflate(parent.getContext(), R.layout.item_load_more, null));这种写法会导致item match_parent不生效 改为下面的写法
                mWrapperHolder = new WrapperHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more,parent,false));
            }
            return mWrapperHolder;
        } else {
            return mAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    // 允许显示"加载更多"item, 并且position为末尾时,拦截
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mShowLoadItem && position == getItemCount() - 1) {
            // 最后一项 不需要做什么额外的事
        } else if (position < mAdapter.getItemCount()){
            // 正常情况
            holder.itemView.setVisibility(View.VISIBLE);
            mAdapter.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        if (mAdapterType == ADAPTER_TYPE_LINEAR) {
            // 线性布局
            return mShowLoadItem ? mAdapter.getItemCount() + 1 : mAdapter.getItemCount();
        }
        return mAdapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        // 当显示"加载更多"条目, 并且位置是最后一个时, wrapper进行拦截
        if (mShowLoadItem && position == getItemCount() - 1) {
            return ITEM_TYPE_LOAD;// 注意要避免和原生adapter返回值重复
        }
        // 其他情况交给原生adapter处理
        return mAdapter.getItemViewType(position);
    }

    public void setLoadItemVisibility(boolean isShow) {
        if (mShowLoadItem != isShow) {
            mShowLoadItem = isShow;
            notifyDataSetChanged();
        }
    }

    public void setLoadItemState(boolean isLoading, int status) {
        setLoadItemState(isLoading, status, getDefaultLoadMoreView(isLoading, status));
    }

    public void setLoadItemState(boolean isLoading, int status, View customLoadView) {
        if(mWrapperHolder==null){
            return;
        }
        ((ViewGroup)mWrapperHolder.itemView).removeAllViews();
        ((ViewGroup)mWrapperHolder.itemView).addView(customLoadView);
    }

    private View getDefaultLoadMoreView(boolean isLoading, int status){
        if (defaultView==null)
            return null;
        tvDefaultLoad = defaultView.findViewById(R.id.item_load_tv);
        pbDefault = defaultView.findViewById(R.id.item_load_pb);
        if(mAdapter.getItemCount()<1){
            tvDefaultLoad.setVisibility(View.GONE);
            pbDefault.setVisibility(View.GONE);
        }
        if (isLoading) {
            tvDefaultLoad.setText("正在加载...");
            tvDefaultLoad.setVisibility(View.VISIBLE);
            pbDefault.setVisibility(View.VISIBLE);
        } else {
            if(status == SwipeToLoadMore.STATUS_LOADING_ERROR){
                tvDefaultLoad.setText("加载失败，请检查网络");
                tvDefaultLoad.setVisibility(View.VISIBLE);
            }else if(status == SwipeToLoadMore.STATUS_LOADING_DONE){
                tvDefaultLoad.setText("没有了");
                tvDefaultLoad.setVisibility(View.VISIBLE);
            }else {
                tvDefaultLoad.setVisibility(View.GONE);
            }
            pbDefault.setVisibility(View.GONE);
        }
        return defaultView;
    }

    class WrapperHolder extends RecyclerView.ViewHolder {

        WrapperHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
