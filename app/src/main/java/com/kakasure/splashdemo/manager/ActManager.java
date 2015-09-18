package com.kakasure.splashdemo.manager;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by Administrator on 2015/9/17.
 */
public class ActManager {
    private static Stack<Activity> mActivityStack;
    private static ActManager mActivityManager;
    private static Object object = new Object();

    private ActManager() {
        mActivityStack = new Stack<Activity>();
    }

    public static ActManager getInstance() {
        if (mActivityManager == null) {
            synchronized (object) {
                if (mActivityManager == null) {
                    mActivityManager = new ActManager();
                }
            }

        }
        return mActivityManager;
    }

    /**
     * 销毁最近使用的activity(前提已经添加进入了管理器)
     *
     * @return true=销毁成功,false=集合为空没有或者activity==null
     */
    public boolean finishActivity() {
        if (mActivityStack.size() == 0)
            return false;
        Activity activity = mActivityStack.lastElement();
        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
            activity = null;
            return true;
        }
        return false;
    }

    /**
     * 将某个activity销毁掉
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            mActivityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获得最近使用的activity实例
     * @return
     */
    public synchronized Activity getCurrentActivity() {
        return mActivityStack.lastElement();
    }

    /**
     * 添加一个Activity
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<Activity>();
        }
        if (mActivityStack.contains(activity)) {
            mActivityStack.remove(activity);
        }
        mActivityStack.add(activity);
    }

    /**
     * 销毁所有的activity.System.exit(0)方式退出app
     */
    public void finishAll() {
        while (true) {
            if (!finishActivity())
                break;
        }
    }

    public int getSize() {
        return null == mActivityStack ? 0 : mActivityStack.size();
    }

    public Stack<Activity> getActivityStack() {
        return mActivityStack;
    }
}
