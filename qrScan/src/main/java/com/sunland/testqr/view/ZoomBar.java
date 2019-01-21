package com.sunland.testqr.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Camera;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.qrcode.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created By YPT
 * On 2018/08/16
 */
public class ZoomBar extends View {
    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private Camera mCamera;
    private boolean hasCreated;
    private int max_zoom;
    private float level_interval;//每一个缩放等级在ZoomBar上的范围
    private int cur_level;//当前的缩放等级
    private Paint mPaint;
    private Paint circle_paint;

    private boolean disable;
    private Timer mTimer;
    private int width;
    private int height;
    private final int ELEMENT_INTERVAL = 30;//加减符号与ZoomBar的间距
    private final int ELEMENT_WIDTH = 30; //


    private Animation fade_in;
    public ZoomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(5);

        fade_in = AnimationUtils.loadAnimation(mContext, R.anim.zoombar_fade_in);

        circle_paint = new Paint();
        circle_paint.setStyle(Paint.Style.FILL);
        circle_paint.setAntiAlias(true);
        circle_paint.setColor(Color.WHITE);
        circle_paint.setStrokeWidth(5);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Camera.Parameters parameters = mCamera.getParameters();
        if (!hasCreated) {
            max_zoom = parameters.getMaxZoom();
            int adjust_max_zoom = (max_zoom) / 3;//将zoom范围控制在较合理的区间
            level_interval = (width - ELEMENT_WIDTH * 2 - ELEMENT_INTERVAL * 4) / adjust_max_zoom - 1;
        }

        hasCreated = true;
        cur_level = parameters.getZoom();
        int adjust_cur_level = cur_level / 3;
        canvas.drawLine(ELEMENT_INTERVAL, height / 2, ELEMENT_INTERVAL + ELEMENT_WIDTH,
                height / 2, mPaint);//minus mark
        canvas.drawLine(ELEMENT_INTERVAL * 2 + ELEMENT_WIDTH, height / 2,
                width - ELEMENT_WIDTH - ELEMENT_INTERVAL * 2, height / 2, mPaint);//progress bar
        canvas.drawLine(width - ELEMENT_INTERVAL, height / 2, width - ELEMENT_WIDTH - ELEMENT_INTERVAL,
                height / 2, mPaint);
        canvas.drawLine(width - ELEMENT_INTERVAL - ELEMENT_WIDTH / 2, (height - ELEMENT_WIDTH) / 2,
                width - ELEMENT_INTERVAL - ELEMENT_WIDTH / 2,
                ELEMENT_WIDTH + (height - ELEMENT_WIDTH) / 2, mPaint);//plus mark
        canvas.drawCircle(ELEMENT_WIDTH + ELEMENT_INTERVAL * 2 + level_interval * (adjust_cur_level) + level_interval / 2,
                height / 2, level_interval / 2, circle_paint);//progress buckle
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(disable)
            return false;
        if(mCamera==null){
            return false;
        }
        int action = event.getActionMasked();
        float x = event.getX();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                clearAnimation();
                setVisibility(View.VISIBLE);
                circle_paint.setColor(Color.GRAY);
                int level = getPointingLevel(x, y);
                if (level >= 0) {
                    adjustCamera(level);
                } else if (level == -1 && cur_level > 0) {
                    adjustCamera(cur_level -= 3);
                    postTask(true);
                } else if (level == -2 && cur_level < max_zoom) {
                    adjustCamera(cur_level += 3);
                    postTask(false);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                int level2 = getPointingLevel(x, y);
                if (level2 >= 0)
                    adjustCamera(level2);
                break;
            case MotionEvent.ACTION_UP:
                circle_paint.setColor(Color.WHITE);
                invalidate();

                if(mTimer!=null)
                mTimer.cancel();

                startAnimation(fade_in);
                setVisibility(View.GONE);
                break;
        }
        return super.onTouchEvent(event);
    }

    private int getPointingLevel(float x, float y) {
        float edge_margin = ELEMENT_WIDTH + ELEMENT_INTERVAL * 2;
        if (x < edge_margin)
            return -1;
        if (x > width - edge_margin)
            return -2;
        return (int) ((x - edge_margin) / (width - 2 * edge_margin) * (max_zoom + 1));
    }

    public void setZoomLevel(int level) {
        if (hasCreated) {
            invalidate();
        }
    }

    private void adjustCamera(int level) {
        Camera.Parameters parameters = mCamera.getParameters();
        if(level<=0){
            level=0;
        }
        if(level>=max_zoom){
            level=max_zoom;
        }
        parameters.setZoom(level);
        mCamera.setParameters(parameters);
        setZoomLevel(level);
    }

    public void setCamera(Camera camera) {
        this.mCamera = camera;
    }

    private void postTask(final boolean isDown){
        mTimer=new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                ((Activity)mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isDown)
                        adjustCamera(cur_level-=3);
                        else{
                            adjustCamera(cur_level+=3);
                        }
                    }
                });
            }
        },500,50);
    }

    public void disable(){
        this.disable=true;
    }
}
