package com.jx.commonlibrary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jx.commonlibrary.common.CommonUtil;

import java.util.List;

public abstract class BaseAdapter<D extends BaseBean, T extends BaseHolder<D>> extends RecyclerView.Adapter<T> {

    protected Context context;
    protected List<D> dataList;

    public BaseAdapter(Context context, List<D> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return createHolder(viewGroup, position);
    }

    protected abstract T createHolder(ViewGroup viewGroup, int position);

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        holder.bindData(dataList.get(position), position);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position, @NonNull List<Object> payloads) {
        if (CommonUtil.isListEmpty(payloads)){
            onBindViewHolder(holder, position);
        }else {
            holder.bindData(dataList.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).getLayoutType();
    }
}
