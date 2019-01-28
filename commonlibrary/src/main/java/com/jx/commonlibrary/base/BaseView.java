package com.jx.commonlibrary.base;

public interface BaseView {
    /**
     * show loading progress
     */
    void showProgress();
    /**
     * hide loading progress
     */
    void hideProgress();
    /**
     * logout app
     */
    void onLogout();

    /**
     * common toast
     * @param msg
     */
    void showToast(String msg);
}
