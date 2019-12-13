package com.jx.commonlibrary.test;

import com.jx.commonlibrary.http.ApiListener;
import com.jx.commonlibrary.http.ApiManager;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoadModel {

    private void load(String url, Map<String, Object> map){
        ApiManager.getInstance().getRetrofit()
                .create(ApiService.class)
                .getString(url, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
