package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Rv_Item_decoration;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Car_info_list_adapter;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.My_force_certi_adapter;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_force_certi_list extends Ac_base {

    @BindView(R.id.force_certis)
    public RecyclerView rv_force_certis;

    private My_force_certi_adapter my_force_certi_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_force_certi_list);
        showLightStatusBar();
        showToolBar(true);
        initView();
    }

    private void initView() {
        setToolBarTitle("强制凭证");
        my_force_certi_adapter = new My_force_certi_adapter(this);

        my_force_certi_adapter.setOnItemClickedListener(new Car_info_list_adapter.OnItemClickedListener() {
            @Override
            public void onClick(int position) {
                hopToActivity(Ac_force_certi_detail.class);
            }
        });

        rv_force_certis.setLayoutManager(new LinearLayoutManager(this));
        rv_force_certis.setAdapter(my_force_certi_adapter);
        rv_force_certis.addItemDecoration(new Rv_Item_decoration(this));
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
