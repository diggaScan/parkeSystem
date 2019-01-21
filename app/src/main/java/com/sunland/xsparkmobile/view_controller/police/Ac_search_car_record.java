package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.customView.CancelableEdit;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.Frgs.Frg_search_result;
import com.sunland.xsparkmobile.view_controller.police.Frgs.Frg_search_tips;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_search_car_record extends Ac_base {

    @BindView(R.id.banner_title)
    public TextView tv_banner_title;
    @BindView(R.id.search_bar)
    public CancelableEdit ce_search_bar;
    @BindView(R.id.frg_container)
    public FrameLayout fl_frg_container;
    @BindView(R.id.search_action)
    public TextView tv_search_aciton;

    private Frg_search_tips frg_search_tips;
    private Frg_search_result frg_search_result;
    private FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_search_car_list);
        showToolBar(false);
        tv_banner_title.setText("搜索");
        initView();
    }

    private void initView() {
        showLightStatusBar();

        ce_search_bar.setOnTextChangeListener(new CancelableEdit.OnTextChangeListener() {
            @Override
            public void beforeTextChange() {

            }

            @Override
            public void onTextChange() {

            }

            @Override
            public void afterTextChange() {
                if (!ce_search_bar.getText().toString().isEmpty()) {
                    tv_search_aciton.setText("搜索");
                } else {
                    tv_search_aciton.setText("取消");
                    fm.beginTransaction().hide(frg_search_result).show(frg_search_tips).commit();
                }
            }
        });
        frg_search_result = new Frg_search_result();
        frg_search_tips = new Frg_search_tips();
        fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.frg_container, frg_search_tips).add(R.id.frg_container, frg_search_result).hide(frg_search_result).commit();

    }

    @OnClick(R.id.search_action)
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.search_action:
                if (tv_search_aciton.getText().toString().equals("取消")) {
                    finish();
                } else {
                    // TODO: 2019/1/21/021 search...
                    fm.beginTransaction().hide(frg_search_tips).show(frg_search_result).commit();
                }
                break;

        }
    }
}
