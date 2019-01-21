package com.sunland.xsparkmobile.view_controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.DialogUtils;

import butterknife.ButterKnife;


public class Ac_base extends AppCompatActivity {

    private Toolbar base_toolbar;
    private FrameLayout base_container;
    private LayoutInflater layoutInflater;

    public DialogUtils dialogUtils;
    public final int QR_REQUEST_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base);
        base_toolbar = findViewById(R.id.base_toolbar);
        base_container = findViewById(R.id.base_container);
        setSupportActionBar(base_toolbar);
        layoutInflater = getLayoutInflater();

        dialogUtils = DialogUtils.getInstance();
    }

    public void setContentLayout(int layout) {
        layoutInflater.inflate(layout, base_container, true);
        ButterKnife.bind(this);
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

    public void startQrScan() {
        Intent intent = new Intent();
        intent.setAction("com.sunland.QR_SCAN");
        startActivityForResult(intent, QR_REQUEST_CODE);
    }


}
