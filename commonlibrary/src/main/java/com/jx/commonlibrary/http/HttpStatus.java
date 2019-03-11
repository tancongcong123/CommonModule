package com.jx.commonlibrary.http;

import com.google.gson.annotations.SerializedName;
import com.jx.commonlibrary.common.CommonUtil;

/**
 * Created by AX on 2017/10/25.
 */

public class HttpStatus{
    @SerializedName("code")
    private int mCode;
    @SerializedName("error")
    private String mError;
    @SerializedName("message")
    private String message;

    public int getCode() {
        return mCode;
    }

    public String getmError() {
        return CommonUtil.isEmpty(message)?mError:message;
    }

    /**
     * API是否请求失败
     *
     * @return 失败返回true, 成功返回false
     * code 301 被挤掉登录
     *      1001 未登录
     */
    public boolean isCodeInvalid() {
        if (isLoginOut()){
            return false;
        }
        return mCode != HttpCode.getInstance().getSuccessCode();
    }

    public boolean isLoginOut(){
        return (mCode == HttpCode.getInstance().getLoginErrorCode() || mCode == HttpCode.getInstance().getLogoutCode());
    }

}
