package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Rv_Item_decoration;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Car_info_list_adapter;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.My_approval_list_adapter;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_approval_list extends Ac_base {
    @BindView(R.id.approval_list)
    public RecyclerView rv_approval_list;
    private My_approval_list_adapter my_approval_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_approval_list);
        showLightStatusBar();
        showToolBar(true);
        setToolBarTitle("我的审批");
        initView();
    }

    private void initView() {

        my_approval_list_adapter = new My_approval_list_adapter(this);
        my_approval_list_adapter.setOnItemClickedListener(new Car_info_list_adapter.OnItemClickedListener() {
            @Override
            public void onClick(int position) {
                hopToActivity(Ac_approval_detail.class);
            }
        });
        rv_approval_list.setAdapter(my_approval_list_adapter);
        rv_approval_list.setLayoutManager(new LinearLayoutManager(this));
        rv_approval_list.addItemDecoration(new Rv_Item_decoration(this));
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
