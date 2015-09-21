package com.kakasure.splashdemo.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.kakasure.splashdemo.R;
import com.kakasure.splashdemo.callback.AnimatorCallback;

import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class FirstAnimator implements DefaultAnimator {
    public View baseView;
    private ImageView start1;
    private ImageView start2;
    private ImageView start3;

    private void initView() {
        start1 = (ImageView) baseView.findViewById(R.id.start1);
        start2 = (ImageView) baseView.findViewById(R.id.start2);
        start3 = (ImageView) baseView.findViewById(R.id.start3);
        start1.setVisibility(View.GONE);
        start2.setVisibility(View.GONE);
        start3.setVisibility(View.GONE);
    }

    @Override
    public void startAnimator(final View view, final AnimatorCallback animatorCallback) {
        if (view != null) {
            baseView = view;
            initView();
            ImageView imageView = (ImageView) view.findViewById(R.id.first_page_img);
            AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
            ObjectAnimator scalexAnimator = ObjectAnimator.ofFloat(imageView, "scaleX", 0.4f, 1f);
            ObjectAnimator scaleyAnimator = ObjectAnimator.ofFloat(imageView, "scaleY", 0.4f, 1f);
            animatorSet.play(alphaAnimator).with(scalexAnimator).with(scaleyAnimator);
            animatorSet.setDuration(1 * 500);
            animatorSet.setInterpolator(new AccelerateInterpolator());
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    animatorCallback.AnimatorStart(0);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animatorCallback.AnimatorComplete(0);
                    startAnimatorSet(start1);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
    }

    private void startAnimatorSet(final View view) {
        ObjectAnimator startAnimatorX1 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f, 1.8f, 1f, 0.7f, 1.4f, 1.0f, 0.8f, 1f);
        ObjectAnimator startAnimatorY1 = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f, 1.8f, 1f, 0.7f, 1.4f, 1.0f, 0.8f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.play(startAnimatorX1).with(startAnimatorY1);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                switch (view.getId()) {
                    case R.id.start1:
                        startAnimatorSet(start2);
                        break;
                    case R.id.start2:
                        startAnimatorSet(start3);
                        break;
                    case R.id.start3:
                        // TODO NOTHING
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    @Override
    public void withChangeAnimator(int position, float positionOffset, List<View> view, float positionOffsetPix, boolean flag) {
        if (view != null) {
            if (positionOffsetPix == 0) {
                view.get(position).setAlpha(1f);
            } else {
                if (flag) {
                    view.get(position).setAlpha(1 - positionOffset);
                } else {
                    view.get(position).setAlpha(positionOffset);
                }
            }
        }
    }
}
