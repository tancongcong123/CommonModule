package com.jx.commonlibrary.base;

public abstract class BasePresenter<T> {

    protected T mView;

    protected abstract void fetchData();

    protected void attachView(T view){
        mView = view;
    }

    protected void detachView(){
        if (mView != null){
            mView = null;
        }
    }

    protected T getView(){
        if (mView == null){
            throw new RuntimeException("view is not attached!");
        }
        return mView;
    }

    protected abstract void onDestory();
}
