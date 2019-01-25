package com.sunland.xsparkmobile.Utils.Anim;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.Map;

public class CollapseExpandAnimManager {

    public final static int EXPAND = 0;
    public final static int COLLAPSE = 1;
    private static CollapseExpandAnimManager manager;

    private ValueAnimator expand_animation;
    private ValueAnimator collapse_animation;

    private Map<View, C_E_bean> targets;

    private C_E_bean cur_bean;

    private CollapseExpandAnimManager() {
        targets = new HashMap<>();
    }

    public static CollapseExpandAnimManager getInstance() {
        if (manager == null) {
            synchronized (CollapseExpandAnimManager.class) {
                if (manager == null) {
                    manager = new CollapseExpandAnimManager();
                }
            }
        }
        return manager;
    }

    public C_E_bean initTarget(View target, int size, OnAnimationExpandListener onAnimationExpandListener
            , onAnimationCollapseListener onAnimationCollapseListener) {
        if (targets.containsKey(target)) {
            return targets.get(target);
        } else {
            cur_bean = new C_E_bean();
            initCEbean(target, cur_bean, size, onAnimationExpandListener, onAnimationCollapseListener);
            targets.put(target, cur_bean);
            return cur_bean;
        }
    }

    private void initCEbean(final View target, C_E_bean c_e_bean, final int size, final OnAnimationExpandListener onAnimationExpandListener
            , final onAnimationCollapseListener onAnimationCollapseListener) {
        c_e_bean.setHeight(size);
        ValueAnimator expand_animator = ValueAnimator.ofFloat(0, 1);
        expand_animator.setDuration(500);
        ValueAnimator.AnimatorUpdateListener expand_listener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (size * value));
                target.setLayoutParams(lp);
                if (onAnimationExpandListener != null) {
                    onAnimationExpandListener.onAnimationExpand();
                }
            }
        };
        ValueAnimator collapse_animator = ValueAnimator.ofFloat(1, 0);
        collapse_animator.setDuration(500);
        ValueAnimator.AnimatorUpdateListener collapse_listener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (size * value));
                target.setLayoutParams(lp);
                if (onAnimationCollapseListener != null) {
                    onAnimationCollapseListener.onAnimationCollapse();
                }
            }
        };
        c_e_bean.setExpand_animator(expand_animator).setExpand_updateListener(expand_listener).setCollapse_animator(collapse_animator)
                .setCollapse_updateListenr(collapse_listener);

    }

    public void start(View view, int mode) {
        if (targets.containsKey(view)) {
            C_E_bean c_e_bean = targets.get(view);
            if (mode == EXPAND) {
                c_e_bean.expand_animator.start();
            } else if (mode == COLLAPSE) {
                c_e_bean.collapse_animator.start();
            }
        } else {
            return;
        }
    }

    public interface OnAnimationExpandListener {
        void onAnimationExpand();
    }

    public interface onAnimationCollapseListener {
        void onAnimationCollapse();
    }
}
