package com.jx.commonlibrary.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * activity管理类
 */
public class AppManager {

    private static Stack<Activity> activityStack;
    private static AppManager appManager;

    public static AppManager getInstance(){
        if (appManager==null){
            appManager = new AppManager();
        }
        return appManager;
    }

    /**
     * 打开一个activity
     * @param activity
     */
    public void addActivity(Activity activity){
        if (activityStack == null){
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除一个activity
     * @param activity
     */
    public void removeActivity(Activity activity){
        if (activityStack == null || activityStack.size()<1){
            return;
        }
        activityStack.remove(activity);
    }

    /**
     * 获取当前处于栈顶的activity
     * @return
     */
    public Activity getCurrentActivity(){
        if (activityStack == null || activityStack.size()<1){
            return null;
        }
        return activityStack.lastElement();
    }

    /**
     * 关闭所有activity
     */
    public void finishAllActivity(){
        if (activityStack == null){
            return;
        }
        for (int i=0;i<activityStack.size();i++){
            if (activityStack.get(i)!=null)
                activityStack.get(i).finish();
        }
        activityStack.clear();
    }

    /**
     * 退出App
     * @param context
     */
    public void exitApp(Context context){
        try {
            finishAllActivity();
            ActivityManager activityMgr= (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            assert activityMgr != null;
            activityMgr.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) { }
    }
}
