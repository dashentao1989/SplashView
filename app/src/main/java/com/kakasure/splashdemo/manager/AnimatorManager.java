package com.kakasure.splashdemo.manager;

import android.util.SparseArray;
import android.view.View;

import com.kakasure.splashdemo.callback.AnimatorCallback;
import com.kakasure.splashdemo.animator.AnimatorFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 * 动画管理类<br/>
 *
 * @author dashentao
 * @date 2015 9-17
 * @since V 1.0
 */
public class AnimatorManager implements AnimatorCallback {
    // 管理View的集合
    private List<View> viewList = new ArrayList<View>();
    private static AnimatorManager mAnimatorManager;
    private static Object object = new Object();
    private SparseArray<Boolean> sparseArray = new SparseArray<Boolean>();

    public static AnimatorManager getInstance() {
        if (mAnimatorManager == null) {
            synchronized (object) {
                if (mAnimatorManager == null) {
                    mAnimatorManager = new AnimatorManager();
                }
            }
        }
        return mAnimatorManager;
    }

    public void add(View view) {
        if (viewList != null) {
            viewList.add(view);
        }
    }

    public void addAll(List<View> view) {
        if (viewList != null) {
            viewList.clear();
            viewList.addAll(view);
        }
    }

    /**
     * ViewPager滑动式渐变动画<br/>
     *
     * @param position
     * @param positionOffset
     */
    public void withChangeAnimator(int position, float positionOffset, float positionOffsetPix, boolean flag) {
        if (sparseArray != null) {
            if (sparseArray.get(position) != null && sparseArray.get(position)) {
                AnimatorFactory.getInstance(position).withChangeAnimator(position, positionOffset, viewList, positionOffsetPix, flag);
            }
        }
    }

    /**
     * 启动相应子页面的动画<br/>
     *
     * @param position
     */
    public void startAnimator(int position) {
        // 对动画做隐藏处理
        if (viewList != null && viewList.size() > 0) {
            for (int i = 0; i < viewList.size(); i++) {
                if (i == position) {
                    viewList.get(i).setVisibility(View.VISIBLE);
                } else {
                    viewList.get(i).setVisibility(View.INVISIBLE);
                }
            }
        }
        AnimatorFactory.getInstance(position).startAnimator(viewList.get(position), this);
    }

    @Override
    public void AnimatorComplete(int position) {
        if (sparseArray != null) {
            sparseArray.put(position, true);
        }
    }

    @Override
    public void AnimatorStart(int position) {
        if (sparseArray != null) {
            sparseArray.put(position, false);
        }
    }
}
