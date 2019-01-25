package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.DialogUtils;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.OnClick;

public class Ac_force_certi_editor extends Ac_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_force_certi_editor);
        showLightStatusBar();
        initView();
    }

    private void initView() {
        setToolBarTitle("信息补录");

    }


    @OnClick(R.id.submit)
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.submit:
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        dialogUtils.loadSuccess("保存数据成功", false);
                    }
                };
                handler.postDelayed(runnable, 1000);

                dialogUtils.showDialog(this, "补录数据中...", DialogUtils.TYPE_PROGRESS, new DialogUtils.OnCancelClickListener() {
                    @Override
                    public void onCancel() {
                        dialogUtils.dialogDismiss();
                        hopToActivity(Ac_record_list.class);

                    }
                }, null);

                break;
        }
    }
}
