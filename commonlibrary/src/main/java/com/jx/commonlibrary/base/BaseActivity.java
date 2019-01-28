package com.jx.commonlibrary.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jx.commonlibrary.common.AppManager;

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    public T mPresenter;

    protected abstract T createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        mPresenter = createPresenter();
        if (mPresenter!=null)
            mPresenter.attachView((V) this);
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

    }
}
