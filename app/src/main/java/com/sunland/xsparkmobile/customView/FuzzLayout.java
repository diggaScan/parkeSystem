package com.sunland.xsparkmobile.customView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

/**
 * Created By YPT on 2019/2/13/013
 * project name: parkeSystem
 */
public class FuzzLayout extends LinearLayout {


    private int touchSlop;
    private float p_down;
    private Context context;

    private RecyclerView mRvView;

    private int init_pos;//recyclerView当前显示的子view的位置

    private int item_count;//recyclerView的item数量

    public FuzzLayout(Context context) {
        this(context, null);
    }

    public FuzzLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public FuzzLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    private void init() {
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

    }

    public void setInit_pos(int init_pos) {
        this.init_pos = init_pos;
    }

    public void setmRvView(RecyclerView mRvView) {
        this.mRvView = mRvView;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                p_down = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                float cur_pos = event.getX();
                float diff = p_down - cur_pos;
                if (diff > 0 && diff > touchSlop) {
                    if (init_pos < item_count - 1) {
                        init_pos++;
                        mRvView.smoothScrollToPosition(init_pos);
                    }
                } else if (diff < 0 && (-diff) > touchSlop) {
                    if (init_pos > 0) {
                        init_pos--;
                        mRvView.smoothScrollToPosition(init_pos);
                    }
                }
                break;
        }
        return true;
    }


    private void moveRvPosNext() {

    }

    private void moveRvPosPre() {

    }
}
