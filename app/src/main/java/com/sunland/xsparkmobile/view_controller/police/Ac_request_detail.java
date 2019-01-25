package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.Ac_base;

public class Ac_request_detail extends Ac_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_request_detail);
        showLightStatusBar();
        showToolBar(true);
        initView();

    }

    private void initView() {
        setToolBarTitle("我的申请单");
    }
}
