package com.sunland.xsparkmobile.view_controller.parkManager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.V_Config;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.Ac_search_car_record;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Ac_function_rv_adapter;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Park_function_adapter;

import butterknife.BindView;

public class Ac_park_funciton extends Ac_base {

    @BindView(R.id.functions)
    public RecyclerView rv_functions;
    private Park_function_adapter park_function_adapter;

    public final static int REQUEST_DRIVE_IN = 0;
    public final static int REQUEST_DRIVE_OUT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_park_funciton);
        showLightStatusBar();
        showToolBar(false);
        initView();
    }

    private void initView() {
        park_function_adapter = new Park_function_adapter(this);
        park_function_adapter.setOnItemClickedListener(new Ac_function_rv_adapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                switch (position) {
                    case 0:
                        startQrScan(REQUEST_DRIVE_IN);
                        break;
                    case 1:
                        startQrScan(REQUEST_DRIVE_OUT);
                        break;
                    case 2:
                        hopToActivity(Ac_search_car_record.class);
                        break;
                }
            }
        });
        rv_functions.setAdapter(park_function_adapter);
        rv_functions.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_DRIVE_IN) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = new Bundle();
                bundle.putString("qr_result", "1");
                bundle.putInt("kind", REQUEST_DRIVE_IN);
                hopToActivity(Ac_qrscan_result.class, bundle);
            } else {
                return;
            }
        } else if (requestCode == REQUEST_DRIVE_OUT) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = new Bundle();
                bundle.putString("qr_result", "1");
                bundle.putInt("kind", REQUEST_DRIVE_OUT);
                hopToActivity(Ac_qrscan_result.class, bundle);
            } else {
                return;
            }
        }
    }
}
