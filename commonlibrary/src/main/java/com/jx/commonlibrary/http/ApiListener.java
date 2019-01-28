package com.jx.commonlibrary.http;

public interface ApiListener {
    void onSuccess(Object result);
    void onFail(String error);
}
