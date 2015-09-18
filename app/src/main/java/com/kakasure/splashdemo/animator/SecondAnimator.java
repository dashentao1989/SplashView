package com.kakasure.splashdemo.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.kakasure.splashdemo.R;
import com.kakasure.splashdemo.callback.AnimatorCallback;

import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class SecondAnimator implements DefaultAnimator {
    private View baseView;
    private ImageView bg1;
    private ImageView bg2;
    private ImageView bg3;
    private ImageView bg4;
    private ImageView bg5;
    private ImageView bg6;
    private ImageView bg7;

    private void initView() {
        bg1 = (ImageView) baseView.findViewById(R.id.second_bg1);
        bg2 = (ImageView) baseView.findViewById(R.id.second_bg2);
        bg3 = (ImageView) baseView.findViewById(R.id.second_bg3);
        bg4 = (ImageView) baseView.findViewById(R.id.second_bg4);
        bg5 = (ImageView) baseView.findViewById(R.id.second_bg5);
        bg6 = (ImageView) baseView.findViewById(R.id.second_bg6);
        bg7 = (ImageView) baseView.findViewById(R.id.second_bg7);
        bg1.setVisibility(View.INVISIBLE);
        bg2.setVisibility(View.INVISIBLE);
        bg3.setVisibility(View.INVISIBLE);
        bg4.setVisibility(View.INVISIBLE);
        bg5.setVisibility(View.INVISIBLE);
        bg6.setVisibility(View.INVISIBLE);
        bg7.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startAnimator(View view, final AnimatorCallback animatorCallback) {
        if (view != null) {
            baseView = view;
            initView();
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
            objectAnimator.setDuration(500);
            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    animatorCallback.AnimatorStart(1);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animatorCallback.AnimatorComplete(1);
                    startAnimatorSet(bg1);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            objectAnimator.start();
        }
    }

    private void startAnimatorSet(final View view) {
        ObjectAnimator startAnimatorX1 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
        ObjectAnimator startAnimatorY1 = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(100);
        animatorSet.setInterpolator(new LinearInterpolator());
        animatorSet.play(startAnimatorX1).with(alphaAnimator).with(startAnimatorY1);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                switch (view.getId()) {
                    case R.id.second_bg1:
                        startAnimatorSet(bg2);
                        break;
                    case R.id.second_bg2:
                        startAnimatorSet(bg3);
                        break;
                    case R.id.second_bg3:
                        startAnimatorSet(bg4);
                        break;
                    case R.id.second_bg4:
                        startAnimatorSet(bg5);
                        break;
                    case R.id.second_bg5:
                        startAnimatorSet(bg6);
                        break;
                    case R.id.second_bg6:
                        startAnimatorSet(bg7);
                        break;
                    case R.id.second_bg7:
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
