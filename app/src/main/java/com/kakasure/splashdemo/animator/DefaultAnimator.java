package com.kakasure.splashdemo.animator;

import android.view.View;

import com.kakasure.splashdemo.callback.AnimatorCallback;

import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 * 动画公用接口<br/>
 */
public interface DefaultAnimator {
    void startAnimator(View view, AnimatorCallback animatorCallback);

    void withChangeAnimator(int position, float positionOffset, List<View> view, float positionOffsetPix, boolean flag);
}
