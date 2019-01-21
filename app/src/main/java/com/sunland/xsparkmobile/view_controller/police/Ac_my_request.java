package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Rv_Item_decoration;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.My_request_list_adapter;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_my_request extends Ac_base {
    @BindView(R.id.request_list)
    public RecyclerView rv_request_list;
    @BindView(R.id.banner_title)
    public TextView tv_banner_title;

    private My_request_list_adapter my_request_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_my_request);
        showToolBar(false);
        tv_banner_title.setText("我的申请");
        initView();
    }

    private void initView() {

        showLightStatusBar();
        my_request_list_adapter = new My_request_list_adapter(this, null);
        rv_request_list.setLayoutManager(new LinearLayoutManager(this));
        rv_request_list.setAdapter(my_request_list_adapter);
        rv_request_list.addItemDecoration(new Rv_Item_decoration(this));

    }

    @OnClick({R.id.search_container, R.id.filter_container})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.search_container:
                hopToActivity(Ac_search_car_record.class);
                break;
            case R.id.filter_container:
                hopToActivity(Ac_filter.class);
                break;
        }
    }
}
