package com.sunland.testqr.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.qrcode.R;
import com.sunland.testqr.camera.CameraManager;
import com.sunland.testqr.decoding.CaptureActivityHandler;
import com.sunland.testqr.decoding.InactivityTimer;
import com.sunland.testqr.view.ViewfinderView;
import com.sunland.testqr.view.ZoomBar;

import java.io.IOException;
import java.util.Vector;

public class CaptureActivity extends Activity implements Callback {

    private CaptureActivityHandler handler;
    private ViewfinderView viewfinderView;
    private ImageView light_bulb;
    private boolean hasSurface;
    private Vector<BarcodeFormat> decodeFormats;
    private String characterSet;
    private InactivityTimer inactivityTimer;

    private final float BEEP_VOLUME = 0.1f;
    private MediaPlayer mediaPlayer;
    private ZoomBar zoombar;

    private float pre_distance;
    private float cur_distance;

    boolean hasScaled; //用户是否进行过多指缩放操作
    boolean isMoving; //用户是否正在进行多指手势操作
    boolean isAnimating;//zoomBar的渐隐动画是否正在执行

    private boolean isOpen;//闪光灯是否已打开

    private Vibrator vibrator;
    private Animation fade_in;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture);
        //ViewUtil.addTopView(getApplicationContext(), this, R.string.scan_card);
        CameraManager.init(getApplication());
        viewfinderView = findViewById(R.id.viewfinder_view);
        viewfinderView.setCameraManager(CameraManager.get());
        zoombar = findViewById(R.id.what);
        hasSurface = false;
        fade_in = AnimationUtils.loadAnimation(this, R.anim.zoombar_fade_in);
        fade_in.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        initBeep();
        light_bulb = findViewById(R.id.lightbulb);
        light_bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Camera camera = CameraManager.get().getCamera();
                if (camera == null) {
                    return;
                }
                Camera.Parameters parameters = camera.getParameters();

                if (!isOpen) {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                    light_bulb.setBackgroundResource(R.drawable.light_bulb_on);
                    isOpen = true;
                } else {
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);
                    light_bulb.setBackgroundResource(R.drawable.light_bulb_off);
                    isOpen = false;
                }
            }
        });
        inactivityTimer = new InactivityTimer(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();

        FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity= Gravity.CENTER;
        Rect rect=CameraManager.get().getFramingRect();
        Log.d("rect", "onResume: "+rect);
        //todo 需要适配
        lp.topMargin=260;
        light_bulb.setLayoutParams(lp);

        if (hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }
        decodeFormats = null;
        characterSet = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        CameraManager.get().closeDriver();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();
    }

    public void handleDecode(Result result, Bitmap barcode) {
        vibrator.vibrate(150);
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
        zoombar.disable();

        inactivityTimer.onActivity();

        String resultString = result.getText();
        if (resultString.equals("")) {
            Toast.makeText(CaptureActivity.this, "Scan failed!", Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", resultString);
            resultIntent.putExtras(bundle);
            this.setResult(RESULT_OK, resultIntent);
        }

        CaptureActivity.this.finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        try {
            CameraManager.get().openDriver(surfaceHolder);
        } catch (IOException ioe) {
            return;
        } catch (RuntimeException e) {
            return;
        }
        if (handler == null) {
            handler = new CaptureActivityHandler(this, decodeFormats,
                    characterSet);
        }
    }

    /**
     * 选择在Activity中完成手势操作的业务逻辑，相较于视图中，避免了手势拦截逻辑的处理,
     * 免于受到自定义控件内部手势逻辑的干扰，减少代码量
     **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (CameraManager.get().getCamera() == null)
            return false;   // 防止当camera.release()后,继续操作相机导致的空指针异常
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                pre_distance = fingersSpacing(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() >= 2) {
                    zoombar.clearAnimation();
                    cur_distance = fingersSpacing(event);
                    if (cur_distance > pre_distance) {
                        handleZoom(CameraManager.get().getCamera(), true);
                    } else {
                        handleZoom(CameraManager.get().getCamera(), false);
                    }
                    pre_distance = cur_distance;
                    hasScaled = true;
                    isMoving = true;
                    setZoombarVisibility(hasScaled, isMoving, isAnimating);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerCount() <= 2) {
                    isMoving = false;
                    setZoombarVisibility(hasScaled, isMoving, isAnimating);
                }
                break;
        }
        return false;
    }

    private float fingersSpacing(MotionEvent event) {
        float x_offset = event.getX(0) - event.getX(1);
        float y_offset = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x_offset * x_offset + y_offset * y_offset);
    }

    private void handleZoom(Camera camera, boolean zoomIn) {
        Camera.Parameters parameters = camera.getParameters();
        if (!parameters.isZoomSupported())
            return;
        int max_zoom = parameters.getMaxZoom();
        int zoom_level = parameters.getZoom();//current zoom level.
        if (zoomIn) {
            if (zoom_level >= 0 && zoom_level < max_zoom) {
                ++zoom_level;
            }
        } else {
            --zoom_level;
        }
        parameters.setZoom(zoom_level);
        zoombar.setZoomLevel(zoom_level);
        camera.setParameters(parameters);
    }

    /**
     * 将手势操作状态和ZoomBar状态绑定。
     *
     * @param hasScaled
     * @param isMoving
     * @param isAnimating
     */
    public void setZoombarVisibility(boolean hasScaled, boolean isMoving, boolean isAnimating) {
        if (hasScaled && isMoving) {
            zoombar.setVisibility(View.VISIBLE);
        }
        if (hasScaled && !isMoving && !isAnimating) {
            zoombar.startAnimation(fade_in);
            zoombar.setVisibility(View.GONE);
        }
        if (hasScaled && isMoving && isAnimating) {
            zoombar.clearAnimation();
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!hasSurface) {
            hasSurface = true;
            initCamera(holder);
            zoombar.setCamera(CameraManager.get().getCamera());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        hasSurface = false;
    }

    public ViewfinderView getViewfinderView() {
        return viewfinderView;
    }

    public Handler getHandler() {
        return handler;
    }

    public void drawViewfinder() {
        viewfinderView.drawViewfinder();
    }

    public void initBeep() {
        if (mediaPlayer == null) {
            setVolumeControlStream(AudioManager.STREAM_MUSIC);
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(beepListener);

            AssetFileDescriptor file = getResources().openRawResourceFd(
                    R.raw.beep);
            try {
                mediaPlayer.setDataSource(file.getFileDescriptor(),
                        file.getStartOffset(), file.getLength());
                file.close();
                mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
                mediaPlayer.prepare();
            } catch (IOException e) {
                mediaPlayer = null;
            }
        }
    }

    private final MediaPlayer.OnCompletionListener beepListener = new MediaPlayer.OnCompletionListener() {
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    };
}