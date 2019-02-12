package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.view.View;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.DialogUtils2;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.OnClick;

public class Ac_approval_detail extends Ac_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_approval_detail);
        showLightStatusBar();
        showToolBar(true);
        initView();
    }

    private void initView() {
        dialogUtils2.initDialog(this);
        setToolBarTitle("详情");
    }

    @OnClick({R.id.reject, R.id.pass})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.reject:
                dialogUtils2.initDialog(this);
                dialogUtils2.setTitle("确定驳回该申请!");
                dialogUtils2.setDescription("");

                dialogUtils2.setOnCancelListener(new DialogUtils2.OnCancelListener() {
                    @Override
                    public void onCancel() {
                        dialogUtils2.dismiss();
                    }
                });

                dialogUtils2.setOnConfirmListener(new DialogUtils2.onConfirmListener() {
                    @Override
                    public void onConfirm() {
                        dialogUtils2.dismiss();
                        hopToActivity(Ac_approval_list.class);
                    }
                });
                dialogUtils2.show();
                break;
            case R.id.pass:
                dialogUtils2.initDialog(this);
                dialogUtils2.setTitle("确定通过该申请!");
                dialogUtils2.setDescription("");

                dialogUtils2.setOnCancelListener(new DialogUtils2.OnCancelListener() {
                    @Override
                    public void onCancel() {
                        dialogUtils2.dismiss();
                    }
                });

                dialogUtils2.setOnConfirmListener(new DialogUtils2.onConfirmListener() {
                    @Override
                    public void onConfirm() {
                        dialogUtils2.dismiss();
                        hopToActivity(Ac_approval_list.class);
                    }
                });
                dialogUtils2.show();
                break;
        }
    }

}
