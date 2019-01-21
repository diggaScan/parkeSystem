/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sunland.testqr.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.google.zxing.ResultPoint;
import com.qrcode.R;
import com.sunland.testqr.camera.CameraManager;

import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {

    private static final int[] SCANNER_ALPHA = {0, 64, 128, 192, 255, 192, 128, 64};
    private static final long ANIMATION_DELAY = 80L;
    private static final int CURRENT_POINT_OPACITY = 0xA0;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 6;

    private CameraManager cameraManager;
    private Paint basic_paint;
    private Paint text_paint;
    private Paint gradient_paint;
    private Bitmap resultBitmap;
    private final int maskColor;
    private final int resultColor;
    private final int laserColor;
    private final int resultPointColor;
    private final int decor_color;
    private final int decor_line_length = 50;
    private int min_scroll_offset;
    private int max_scroll_offset;

    private final int decor_offset = 4; //adjust the corner decor
    private boolean isInitialed;
    private int current_y;
    private float offset = 5.0f; //offset value of scroll bar vertically
    private int redraw_interval = 10;

    private int scannerAlpha;
    private List<ResultPoint> possibleResultPoints;
    private List<ResultPoint> lastPossibleResultPoints;
    private String testString = "请将二维码/条形码放入扫描框中";

    // This constructor is used when the class is built from an XML resource.
    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize these once for performance rather than calling them every time in onDraw().
        basic_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        basic_paint.setStrokeWidth(10);

        text_paint = new Paint();
        text_paint.setColor(Color.LTGRAY);
        text_paint.setAntiAlias(true);
        text_paint.setTextSize(40);
        text_paint.setTextAlign(Paint.Align.CENTER);

        gradient_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        LinearGradient lg = new LinearGradient(0, 0, 200, 300, Color.GREEN, Color.WHITE, Shader.TileMode.MIRROR);
        gradient_paint.setShader(lg);

        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        resultColor = resources.getColor(R.color.result_view);
        laserColor = resources.getColor(R.color.viewfinder_laser);
        resultPointColor = resources.getColor(R.color.possible_result_points);
        decor_color = resources.getColor(R.color.decor);
        scannerAlpha = 0;
        possibleResultPoints = new ArrayList(5);
        lastPossibleResultPoints = null;

    }

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (cameraManager == null) {
            return; // not ready yet, early draw before done configuring
        }
        Rect frame = cameraManager.getFramingRect();
        Rect previewFrame = cameraManager.getFramingRectInPreview();
        if (frame == null || previewFrame == null) {
            return;
        }

        min_scroll_offset = frame.top + 20;
        max_scroll_offset = frame.bottom - 20;
        if (!isInitialed) {
            current_y = min_scroll_offset;
            isInitialed = true;
        }

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        // Draw the exterior (i.e. outside the framing rect) darkened
        basic_paint.setColor(resultBitmap != null ? resultColor : maskColor);
        canvas.drawRect(0, 0, width, frame.top, basic_paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, basic_paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, basic_paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, basic_paint);

        //decor part on the corner of the Rect
        basic_paint.setColor(decor_color);
        canvas.drawLine(frame.left, frame.top + decor_offset, frame.left + decor_line_length, frame.top + decor_offset, basic_paint);
        canvas.drawLine(frame.right - decor_line_length, frame.top + decor_offset, frame.right, frame.top + decor_offset, basic_paint);
        canvas.drawLine(frame.left + decor_offset, frame.top, frame.left + decor_offset, frame.top + decor_line_length, basic_paint);
        canvas.drawLine(frame.left + decor_offset, frame.bottom, frame.left + decor_offset, frame.bottom - decor_line_length, basic_paint);
        canvas.drawLine(frame.left, frame.bottom - decor_offset, frame.left + decor_line_length, frame.bottom - decor_offset, basic_paint);
        canvas.drawLine(frame.right, frame.bottom - decor_offset, frame.right - decor_line_length, frame.bottom - decor_offset, basic_paint);
        canvas.drawLine(frame.right - decor_offset, frame.bottom, frame.right - decor_offset, frame.bottom - decor_line_length, basic_paint);
        canvas.drawLine(frame.right - decor_offset, frame.top, frame.right - decor_offset, frame.top + decor_line_length, basic_paint);


        canvas.drawText(testString, frame.centerX(), frame.bottom + 80, text_paint);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            basic_paint.setAlpha(CURRENT_POINT_OPACITY);
            canvas.drawBitmap(resultBitmap, null, frame, basic_paint);
        } else {
            // Draw a red "laser scanner" line through the middle to show decoding is active
            basic_paint.setColor(decor_color);
//    paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
            scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
            int middle = frame.height() / 2 + frame.top;

//    canvas.drawRect(frame.left + 2, middle - 1, frame.right - 1, middle + 2, paint);

            //draw scroll bar
            RectF rect = new RectF(frame.left + 10, current_y, frame.right - 10, current_y + 15);
            canvas.drawOval(rect, gradient_paint);

            current_y += offset;
            if (current_y >= max_scroll_offset) {
                current_y = min_scroll_offset;
            }

            float scaleX = frame.width() / (float) previewFrame.width();
            float scaleY = frame.height() / (float) previewFrame.height();

            List<ResultPoint> currentPossible = possibleResultPoints;
            List<ResultPoint> currentLast = lastPossibleResultPoints;
            int frameLeft = frame.left;
            int frameTop = frame.top;
            if (currentPossible.isEmpty()) {
                lastPossibleResultPoints = null;
            } else {
                possibleResultPoints = new ArrayList(5);
                lastPossibleResultPoints = currentPossible;
                basic_paint.setAlpha(CURRENT_POINT_OPACITY);
                basic_paint.setColor(resultPointColor);
                synchronized (currentPossible) {
                    for (ResultPoint point : currentPossible) {
                        canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                                frameTop + (int) (point.getY() * scaleY),
                                POINT_SIZE, basic_paint);
                    }
                }
            }
            if (currentLast != null) {
                basic_paint.setAlpha(CURRENT_POINT_OPACITY / 2);
                basic_paint.setColor(resultPointColor);
                synchronized (currentLast) {
                    float radius = POINT_SIZE / 2.0f;
                    for (ResultPoint point : currentLast) {
                        canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                                frameTop + (int) (point.getY() * scaleY),
                                radius, basic_paint);
                    }
                }
            }

            // Request another update at the animation interval, but only repaint the laser line,
            // not the entire viewfinder mask.
            postInvalidateDelayed(redraw_interval,
                    frame.left - POINT_SIZE,
                    frame.top - POINT_SIZE,
                    frame.right + POINT_SIZE,
                    frame.bottom + POINT_SIZE);
        }
    }

    public void drawViewfinder() {
        Bitmap resultBitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (resultBitmap != null) {
            resultBitmap.recycle();
        }
        invalidate();
    }

    /**
     * Draw a bitmap with the result points highlighted instead of the live scanning display.
     *
     * @param barcode An image of the decoded barcode.
     */
    public void drawResultBitmap(Bitmap barcode) {
        resultBitmap = barcode;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        List<ResultPoint> points = possibleResultPoints;
        synchronized (points) {
            points.add(point);
            int size = points.size();
            if (size > MAX_RESULT_POINTS) {
                // trim it
                points.subList(0, size - MAX_RESULT_POINTS / 2).clear();
            }
        }
    }

}
