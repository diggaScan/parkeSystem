package com.sunland.xsparkmobile.view_controller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sunland.xsparkmobile.BuildConfig;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_login extends CheckSelfPermissionActivity {


    @BindView(R.id.user_name)
    public EditText et_username;
    @BindView(R.id.password)
    public EditText et_password;
    private boolean hasLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_login);
        showToolBar(false);
        if (BuildConfig.DEBUG) {
            showToolBar(true);
            ib_nav_back.setVisibility(View.GONE);
            tv_option_text.setVisibility(View.VISIBLE);
            tv_option_text.setText("设置");
        }

        initVariables();
    }

    private void initVariables() {
//        SharedPreferences sp = getSharedPreferences(V_Config.URL_SP, Context.MODE_PRIVATE);
//        sp.getString("ip", null);
//        sp.getString("port", null);
//        sp.getString("desc", null);
    }

    @OnClick({R.id.email_sign_in_button, R.id.option_text, R.id.map_download})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.email_sign_in_button:
                if (et_username.getText().toString().isEmpty()) {
                    Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Bundle bundle = new Bundle();
                        //1 拖车司机，2停车场管理员，3警员
                        bundle.putString("auth", et_username.getText().toString());
                        hopToActivity(Ac_index.class, bundle);
                        hasLogin = true;
                        dialogUtils.dialogDismiss();
                    }
                };
                handler.postDelayed(runnable, 1000);
                dialogUtils.showDialog(Ac_login.this, "登录中...", DialogUtils.TYPE_PROGRESS, new DialogUtils.OnCancelClickListener() {
                    @Override
                    public void onCancel() {
                        handler.removeCallbacks(runnable);
                    }
                }, null);
                break;
            case R.id.option_text:
                hopToActivity(Ac_setting.class);
                break;
            case R.id.map_download:
                startActivity(new Intent(this.getApplicationContext(),
                        com.amap.api.maps.offlinemap.OfflineMapActivity.class));
                break;
        }


//        if (et_password.getText().toString().isEmpty()) {
//            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
//            return;
//        }


    }

    @Override
    protected void onStop() {
        super.onStop();
        if (hasLogin)
            finish();
    }
}
