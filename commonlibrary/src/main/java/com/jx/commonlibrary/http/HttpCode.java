package com.jx.commonlibrary.http;

public class HttpCode {

    private static HttpCode httpCode;

    public static HttpCode getInstance(){
        synchronized (ApiManager.class){
            if (httpCode==null){
                httpCode = new HttpCode();
            }
        }
        return httpCode;
    }

    private int successCode = 1;
    private int loginErrorCode = 20001;
    private int logoutCode = 20002;

    public int getSuccessCode() {
        return successCode;
    }

    public void setSuccessCode(int successCode) {
        this.successCode = successCode;
    }

    public int getLoginErrorCode() {
        return loginErrorCode;
    }

    public void setLoginErrorCode(int loginErrorCode) {
        this.loginErrorCode = loginErrorCode;
    }

    public int getLogoutCode() {
        return logoutCode;
    }

    public void setLogoutCode(int logoutCode) {
        this.logoutCode = logoutCode;
    }
}
