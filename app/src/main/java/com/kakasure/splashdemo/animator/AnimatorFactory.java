package com.kakasure.splashdemo.animator;

import android.util.Log;

/**
 * Created by Administrator on 2015/9/17.
 * 简单工厂类<br/>
 */
public class AnimatorFactory {
    public static final String TAG = AnimatorFactory.class.getSimpleName();

    public static DefaultAnimator getInstance(int position) {
        switch (position) {
            case 0:
                return new FirstAnimator();
            case 1:
                return new SecondAnimator();
            case 2:
                return new ThirdAnimator();
            default:
                Log.i(TAG, "you know, it will not be happened!");
                break;
        }
        return null;
    }
}
