package com.jx.commonlibrary.common;

import java.util.List;

public class CommonUtil {

    public static boolean isEmpty(String content){
        if (content==null || content.equals("")){
            return true;
        }
        return false;
    }

    public static boolean isListEmpty(List list){
        if (list==null || list.size()<1){
            return true;
        }
        return false;
    }
}
