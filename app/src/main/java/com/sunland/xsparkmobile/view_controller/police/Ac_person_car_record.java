package com.sunland.xsparkmobile.view_controller.police;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.VersionCheckUtils;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.BindView;

public class Ac_person_car_record extends Ac_base {
    @BindView(R.id.car_records)
    public RecyclerView rv_car_records;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_person_car_record);
        showToolBar(false);
        initView();
    }

    private void initView() {
        if (VersionCheckUtils.isAboveVersion(Build.VERSION_CODES.M)) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.yellow_white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


    }
}
