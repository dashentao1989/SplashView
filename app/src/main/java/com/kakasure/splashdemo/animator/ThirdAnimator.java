package com.kakasure.splashdemo.animator;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.kakasure.splashdemo.R;
import com.kakasure.splashdemo.callback.AnimatorCallback;
import com.kakasure.splashdemo.manager.ActManager;
import com.kakasure.splashdemo.utils.AppUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/9/17.
 */
public class ThirdAnimator implements DefaultAnimator {
    private View baseView;
    private ImageView cash;
    private ImageView order;
    private ImageView box;
    private ImageView arrow1;
    private ImageView arrow2;
    private ImageView arrow3;

    private void initView() {
        cash = (ImageView) baseView.findViewById(R.id.third_cash);
        order = (ImageView) baseView.findViewById(R.id.third_order);
        box = (ImageView) baseView.findViewById(R.id.third_box);
        arrow1 = (ImageView) baseView.findViewById(R.id.third_arrow_1);
        arrow2 = (ImageView) baseView.findViewById(R.id.third_arrow_2);
        arrow3 = (ImageView) baseView.findViewById(R.id.third_arrow_3);
        cash.setVisibility(View.INVISIBLE);
        order.setVisibility(View.INVISIBLE);
        box.setVisibility(View.INVISIBLE);
        arrow1.setVisibility(View.INVISIBLE);
        arrow2.setVisibility(View.INVISIBLE);
        arrow3.setVisibility(View.INVISIBLE);
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
                    animatorCallback.AnimatorStart(2);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    animatorCallback.AnimatorComplete(2);
                    startAnimatorSet(box);
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
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(150);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        switch (view.getId()) {
            case R.id.third_box:
                ObjectAnimator alphaAnimatorBox = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                animatorSet.play(alphaAnimatorBox);
                break;
            case R.id.third_cash:
                ObjectAnimator alphaAnimatorCash = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                animatorSet.play(alphaAnimatorCash);
                break;
            case R.id.third_order:
                ObjectAnimator alphaAnimatorOrder = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                animatorSet.play(alphaAnimatorOrder);
                break;
            case R.id.third_arrow_1:
                float currentX1 = view.getTranslationX();
                float currentY1 = view.getTranslationY();
                ObjectAnimator arrowX1 = ObjectAnimator.ofFloat(view, "translationX", currentX1 - AppUtils.dip2px(ActManager.getInstance().getCurrentActivity(), 8), currentX1);
                ObjectAnimator arrowY1 = ObjectAnimator.ofFloat(view, "translationY", currentY1 - AppUtils.dip2px(ActManager.getInstance().getCurrentActivity(), 8), currentY1);
                animatorSet.play(arrowX1).with(arrowY1);
                break;
            case R.id.third_arrow_2:
                float currentX2 = view.getTranslationX();
                ObjectAnimator arrowX2 = ObjectAnimator.ofFloat(view, "translationX", currentX2 + AppUtils.dip2px(ActManager.getInstance().getCurrentActivity(), 8), currentX2);
                animatorSet.play(arrowX2);
                break;
            case R.id.third_arrow_3:
                float currentX3 = view.getTranslationX();
                float currentY3 = view.getTranslationY();
                ObjectAnimator arrowX3 = ObjectAnimator.ofFloat(view, "translationX", currentX3 - AppUtils.dip2px(ActManager.getInstance().getCurrentActivity(), 8), currentX3);
                ObjectAnimator arrowY3 = ObjectAnimator.ofFloat(view, "translationY", currentY3 + AppUtils.dip2px(ActManager.getInstance().getCurrentActivity(), 8), currentY3);
                animatorSet.play(arrowX3).with(arrowY3);
                break;
            default:
                break;
        }
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                switch (view.getId()) {
                    case R.id.third_box:
                        startAnimatorSet(cash);
                        break;
                    case R.id.third_cash:
                        startAnimatorSet(order);
                        break;
                    case R.id.third_order:
                        startAnimatorSet(arrow1);
                        break;
                    case R.id.third_arrow_1:
                        startAnimatorSet(arrow2);
                        break;
                    case R.id.third_arrow_2:
                        startAnimatorSet(arrow3);
                        break;
                    case R.id.third_arrow_3:
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
