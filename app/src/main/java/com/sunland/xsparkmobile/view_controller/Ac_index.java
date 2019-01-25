package com.sunland.xsparkmobile.view_controller;

import android.content.Intent;
import android.os.Bundle;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.parkManager.Ac_park_funciton;
import com.sunland.xsparkmobile.view_controller.parkManager.Ac_qrscan_result;
import com.sunland.xsparkmobile.view_controller.police.Ac_functions;
import com.sunland.xsparkmobile.view_controller.tow_car.Ac_Nav;

public class Ac_index extends Ac_base {

    private String auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_main);
        handleIntent();
        hopByFlavour();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                auth = bundle.getString("auth");
            }
        }
    }

    private void hopByFlavour() {
        switch (auth) {
            case "1":
                hopToActivity(Ac_Nav.class);
                break;
            case "2":
                hopToActivity(Ac_park_funciton.class);
                break;
            case "3":
                hopToActivity(Ac_functions.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        switch (requestCode) {
            case QR_REQUEST_CODE:
                String result = data.getStringExtra("result");
                Bundle bundle = new Bundle();
                bundle.putString("qr_result", result);
                hopToActivity(Ac_qrscan_result.class, bundle);
                break;
        }
    }


}
