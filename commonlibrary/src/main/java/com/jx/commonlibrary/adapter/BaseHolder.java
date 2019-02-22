package com.jx.commonlibrary.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jx.commonlibrary.common.CommonUtil;
import com.jx.commonlibrary.common.GlideApp;

public abstract class BaseHolder<D> extends RecyclerView.ViewHolder {

    protected Context context;

    public BaseHolder(Context context, @NonNull View itemView) {
        super(itemView);
        this.context = context;
    }

    protected abstract void bindData(D obj, int position);

    public void setText(TextView textView, String content){
        textView.setText(CommonUtil.isEmpty(content)?"":content);
    }

    public void setImage(ImageView imageView, String path, int errorRes, int placeHolder){
        GlideApp.with(context).load(path).fitCenter().error(errorRes).placeholder(placeHolder).into(imageView);
    }
}
