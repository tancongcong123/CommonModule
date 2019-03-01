package com.jx.commonlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jx.commonlibrary.common.CommonUtil;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment implements BaseView{

    public T mPresenter;
    private Context context;

    public View getRootView() {
        return rootView;
    }

    private View rootView;

    public abstract T createPresenter();
    public abstract int getLayoutId();
    public abstract void initView();
    public abstract void loadData();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public Context getContext(){
        return context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = createPresenter();
        if (mPresenter!=null)
            mPresenter.attachView((V) this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView==null){
            rootView = inflater.inflate(getLayoutId(), container, false);
            initView();
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onLogout() {

    }

    @Override
    public void showToast(String msg) {
        if (!CommonUtil.isEmpty(msg)){
            Toast.makeText(getActivity() ,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
