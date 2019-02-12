package com.sunland.xsparkmobile.view_controller.police;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sunland.xsparkmobile.GlideApp;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Ac_function_rv_adapter;

import butterknife.BindView;

public class Ac_functions extends Ac_base {

    @BindView(R.id.bkg)
    public ImageView iv_bkg;
    @BindView(R.id.functions)
    public RecyclerView rv__functions;

    @BindView(R.id.banner_content)
    public TextView tv_banner_content;

    private int backPressed_num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_functions);
        showToolBar(false);
        initView();
    }

    private void initView() {

        GlideApp.with(this).asBitmap()
                .load(R.drawable.sky2)
                .into(iv_bkg);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        Ac_function_rv_adapter adapter = new Ac_function_rv_adapter(this);
        adapter.setOnItemClickListener(new Ac_function_rv_adapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                switch (position) {
                    case 0:
                        hopToActivity(Ac_force_certi_list.class);
                        break;
                    case 1:
                        hopToActivity(Ac_record_list.class);
                        break;
                    case 2:
                        hopToActivity(Ac_request_list.class);
                        break;
                    case 3:
                        hopToActivity(Ac_approval_list.class);
                        break;
                    case 4:
                        hopToActivity(Ac_search_car_record.class);
                        break;
                    case 5:
                        startQrScan(0);
                        break;
                }
            }
        });
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rv__functions.setLayoutManager(manager);
        rv__functions.setAdapter(adapter);

        // TODO: 2019/1/18/018 debug content
        String debug_content = "暂扣车辆" + "<font color=\"#ff884d\">  浙AE1789  </font>"
                + "放行申请 " + "<font color=\"#00e600\">   已通过  </font>";
        tv_banner_content.setText(Html.fromHtml(debug_content));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == QR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

            }
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressed_num != 1) {
            backPressed_num++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressed_num--;
                }
            }, 2500);
            Toast.makeText(this, "再按一次，退出应用", Toast.LENGTH_SHORT).show();
        } else {
            finish();
        }
    }
}
