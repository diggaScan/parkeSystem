package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.view.View;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.OnClick;

public class Ac_force_certi_detail extends Ac_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_force_certi_detail);
        showLightStatusBar();

        initView();
    }

    private void initView() {
        setToolBarTitle("强制凭证详情");
        tv_option_text.setVisibility(View.VISIBLE);
        tv_option_text.setText("编辑");

    }

    @OnClick(R.id.option_text)
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.option_text:
                hopToActivity(Ac_force_certi_editor.class);
                break;
        }
    }
}
