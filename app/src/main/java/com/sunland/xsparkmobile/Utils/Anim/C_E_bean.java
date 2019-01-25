package com.sunland.xsparkmobile.Utils.Anim;

import android.animation.ValueAnimator;

public class C_E_bean {
    public ValueAnimator expand_animator;
    public ValueAnimator collapse_animator;
    public ValueAnimator.AnimatorUpdateListener expand_updateListener;
    public ValueAnimator.AnimatorUpdateListener collapse_updateListenr;
    public int height;

    public C_E_bean setExpand_animator(ValueAnimator expand_animator) {
        this.expand_animator = expand_animator;
        return this;
    }

    public C_E_bean setCollapse_animator(ValueAnimator collapse_animator) {
        this.collapse_animator = collapse_animator;
        return this;
    }

    public C_E_bean setExpand_updateListener(ValueAnimator.AnimatorUpdateListener expand_updateListener) {
        this.expand_updateListener = expand_updateListener;
        this.expand_animator.addUpdateListener(expand_updateListener);
        return this;
    }


    public C_E_bean setCollapse_updateListenr(ValueAnimator.AnimatorUpdateListener collapse_updateListenr) {
        this.collapse_updateListenr = collapse_updateListenr;
        this.collapse_animator.addUpdateListener(collapse_updateListenr);
        return this;
    }

    public C_E_bean setHeight(int height) {
        this.height = height;
        return this;
    }


}
