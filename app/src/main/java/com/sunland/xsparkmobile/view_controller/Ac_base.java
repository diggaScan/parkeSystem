package com.sunland.xsparkmobile.view_controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.amap.api.maps.MapsInitializer;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.DialogUtils;
import com.sunland.xsparkmobile.Utils.DialogUtils2;
import com.sunland.xsparkmobile.Utils.VersionCheckUtils;

import butterknife.ButterKnife;


public class Ac_base extends AppCompatActivity {

    private Toolbar base_toolbar;
    private FrameLayout base_container;
    private LayoutInflater layoutInflater;
    private TextView tv_banner_title;
    public DialogUtils dialogUtils;
    public DialogUtils2 dialogUtils2;
    public ImageButton ib_nav_back;
    public TextView tv_option_text;
    public ImageButton ib_option_view;
    public final int QR_REQUEST_CODE = 0;

    public boolean isInit = true;//activity是否为初始化状态
    public int hop_src;//跳入该页面的来源

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base);
        base_toolbar = findViewById(R.id.base_toolbar);
        base_container = findViewById(R.id.base_container);
        tv_banner_title = findViewById(R.id.banner_title);
        ib_nav_back = findViewById(R.id.nav_back);
        ib_nav_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_option_text = findViewById(R.id.option_text);
        ib_option_view = findViewById(R.id.option_image);

        setSupportActionBar(base_toolbar);
        layoutInflater = getLayoutInflater();
        dialogUtils = DialogUtils.getInstance();
        dialogUtils2 = DialogUtils2.getInstance();

        MapsInitializer.sdcardDir = Environment.getExternalStorageDirectory() + "/amapOfflineMap";
    }

    public void setContentLayout(int layout) {
        layoutInflater.inflate(layout, base_container, true);
        ButterKnife.bind(this);
    }

    public void setToolBarTitle(String title) {
        tv_banner_title.setText(title);
    }

    public void showToolBar(boolean show) {
        if (show) {
            base_toolbar.setVisibility(View.VISIBLE);
        } else {
            base_toolbar.setVisibility(View.GONE);
        }
    }


    public void hopToActivity(Class<? extends AppCompatActivity> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void hopToActivity(Class<? extends AppCompatActivity> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    public void hopToActivityForResult(Class<? extends AppCompatActivity> clazz, int request_code) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, request_code);
    }

    public void hopToActivityForResult(Class<? extends AppCompatActivity> clazz, Bundle bundle, int request_code) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, request_code);
    }

    public void startQrScan(int requestCode) {
        Intent intent = new Intent();
        intent.setAction("com.sunland.QR_SCAN");
        startActivityForResult(intent, requestCode);
    }

    public void showLightStatusBar() {
        if (VersionCheckUtils.isAboveVersion(Build.VERSION_CODES.M)) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.yellow_white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
