package com.sunland.xsparkmobile.Utils.Anim;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sunland.xsparkmobile.R;

public class RotateAnimationManager {

    public final static int EXPAND = 0;
    public final static int COLLAPSE = 1;

    private static RotateAnimationManager manager;
    private Animation rotate_expand;
    private Animation rotate_collapse;

    private RotateAnimationManager() {
    }

    public static RotateAnimationManager getInstance() {
        if (manager == null) {
            synchronized (RotateAnimationManager.class) {
                if (manager == null) {
                    manager = new RotateAnimationManager();
                }
            }
        }
        return manager;
    }

    public void initAnim(Context context, boolean setFillAfter) {
        rotate_expand = AnimationUtils.loadAnimation(context, R.anim.cate_arrow_expand);
        rotate_collapse = AnimationUtils.loadAnimation(context, R.anim.cate_arrow_collapse);
        rotate_collapse.setFillAfter(setFillAfter);
        rotate_expand.setFillAfter(setFillAfter);
    }

    public void start(View view, int mode) {
        if (mode == EXPAND) {
            view.startAnimation(rotate_expand);
        } else if (mode == COLLAPSE) {
            view.startAnimation(rotate_collapse);
        }
    }


}
