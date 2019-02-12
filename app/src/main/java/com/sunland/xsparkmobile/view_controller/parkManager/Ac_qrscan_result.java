package com.sunland.xsparkmobile.view_controller.parkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.V_Config;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.Ac_record_detail;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_qrscan_result extends Ac_base {

    private String result;

    private int request_kind;
    @BindView(R.id.result_icon)
    public ImageView iv_result_icon;
    @BindView(R.id.scan_result)
    public TextView tv_scan_result;
    @BindView(R.id.enter_time)
    public TextView tv_enter_time;
    @BindView(R.id.tow_car_num)
    public TextView tv_toll_car_num;
    @BindView(R.id.event_id)
    public TextView tv_event_id;
    @BindView(R.id.action_button)
    public TextView tv_action_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_qrscan_result);
        showToolBar(true);
        showLightStatusBar();
        handleIntent();
        showResult();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                result = bundle.getString("qr_result");
                request_kind = bundle.getInt("kind");

            }
        }
    }

    private void showResult() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String date = simpleDateFormat.format(System.currentTimeMillis());

        if (request_kind == Ac_park_funciton.REQUEST_DRIVE_IN) {
            tv_scan_result.setText("完成车辆进场扫描");
            tv_enter_time.setText("进场时间:" + date);
        } else if (request_kind == Ac_park_funciton.REQUEST_DRIVE_OUT) {
            tv_scan_result.setText("完成车辆驶离扫描");
            tv_enter_time.setText("出场时间:" + date);
        }
        switch (result) {
            case "1"://扫描成功
                iv_result_icon.setImageResource(R.drawable.ic_tick);

                tv_toll_car_num.setText("拖车号牌:" + "浙ATC110");
                tv_event_id.setText("移送单号:Z201900177");

                break;
            case "2"://扫描失败
                iv_result_icon.setImageResource(R.drawable.ic_delete);
                tv_scan_result.setText("扫描失败，请重新扫描");
                tv_enter_time.setText("进场时间:" + date);
                tv_toll_car_num.setText("拖车号牌:" + "浙ATC110");
                tv_event_id.setText("移送单号:Z201900177");
                tv_action_button.setText("重新扫描");
                break;
            case "3":
                break;
        }
    }

    @OnClick(R.id.action_button)
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.action_button:
                Bundle bundle = new Bundle();
                bundle.putInt("hop_source", V_Config.ACTIVITY_PARK_FUNCTION);
                hopToActivity(Ac_record_detail.class, bundle);
//                if (request_kind == Ac_park_funciton.REQUEST_DRIVE_IN) {
//                    startQrScan(Ac_park_funciton.REQUEST_DRIVE_IN);
//                } else if (request_kind == Ac_park_funciton.REQUEST_DRIVE_OUT) {
//                    startQrScan(Ac_park_funciton.REQUEST_DRIVE_OUT);
//                }
                break;
        }
    }


}
