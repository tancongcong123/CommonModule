package com.jx.commonlibrary.http;

/**
 * Created by AX on 2017/10/25.
 */

public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

}
