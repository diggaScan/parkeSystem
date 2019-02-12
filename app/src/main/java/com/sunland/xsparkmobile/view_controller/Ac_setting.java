package com.sunland.xsparkmobile.view_controller;

import android.os.Bundle;

import com.sunland.xsparkmobile.R;

public class Ac_setting extends Ac_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_setting);
        showLightStatusBar();
        showToolBar(true);
        initView();
    }

    private void initView() {

    }

}
