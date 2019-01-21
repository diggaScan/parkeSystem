package com.sunland.xsparkmobile.view_controller.parkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_qrscan_result extends Ac_base {

    private String result;

    @BindView(R.id.result_icon)
    public ImageView iv_result_icon;
    @BindView(R.id.scan_result)
    public TextView tv_scan_result;
    @BindView(R.id.enter_time)
    public TextView tv_enter_time;
    @BindView(R.id.toll_car_num)
    public TextView tv_toll_car_num;
    @BindView(R.id.event_id)
    public TextView tv_event_id;
    @BindView(R.id.action_button)
    public TextView tv_action_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_qrscan_result);
        showToolBar(false);
        handleIntent();
        showResult();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                result = bundle.getString("qr_result");
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showResult() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String date = simpleDateFormat.format(System.currentTimeMillis());
        switch (result) {
            case "1"://扫描成功
                iv_result_icon.setImageResource(R.drawable.ic_tick);
                tv_scan_result.setText("完成拖车进场扫描");
                tv_enter_time.setText("进场时间:" + date);
                tv_toll_car_num.setText("拖车号牌:" + "浙ATC110");
                tv_event_id.setText("移送单号:Z201900177");
                tv_action_button.setText("再次扫描");
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
                startQrScan();
                break;
        }
    }


}
