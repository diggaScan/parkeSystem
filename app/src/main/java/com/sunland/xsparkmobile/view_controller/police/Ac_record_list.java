package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sunland.xsparkmobile.Bean.CarStatus;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Rv_Item_decoration;
import com.sunland.xsparkmobile.V_Config;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Car_info_list_adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_record_list extends Ac_base {
    @BindView(R.id.car_records)
    public RecyclerView rv_car_records;
    @BindView(R.id.banner_title)
    public TextView tv_banner_title;
    private Car_info_list_adapter car_info_list_adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_record_list);
        showToolBar(true);
        ib_option_view.setVisibility(View.VISIBLE);
        setToolBarTitle("我的处理记录");
        initView();
    }

    private void initView() {

        showLightStatusBar();

        List<CarStatus> dataSet = new ArrayList<>();
        dataSet.add(new CarStatus(1));
        dataSet.add(new CarStatus(1));
        dataSet.add(new CarStatus(0));
        dataSet.add(new CarStatus(2));
        dataSet.add(new CarStatus(2));
        dataSet.add(new CarStatus(2));
        dataSet.add(new CarStatus(2));
        dataSet.add(new CarStatus(2));
        dataSet.add(new CarStatus(2));
        dataSet.add(new CarStatus(2));
        dataSet.add(new CarStatus(6));
        dataSet.add(new CarStatus(7));
        dataSet.add(new CarStatus(11));
        dataSet.add(new CarStatus(11));
        dataSet.add(new CarStatus(11));
        dataSet.add(new CarStatus(11));

        car_info_list_adapter = new Car_info_list_adapter(this, dataSet);
        car_info_list_adapter.setOnItemClickedListener(new Car_info_list_adapter.OnItemClickedListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("hop_source", V_Config.SUPPLEMENT_RECORD);
                hopToActivity(Ac_record_detail.class, bundle);
            }
        });
        rv_car_records.setAdapter(car_info_list_adapter);
        rv_car_records.setLayoutManager(new LinearLayoutManager(this));
        rv_car_records.addItemDecoration(new Rv_Item_decoration(this));
    }

    @OnClick({R.id.search_container, R.id.filter_container, R.id.option_image})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.search_container:
                hopToActivity(Ac_search_car_record.class);
                break;
            case R.id.filter_container:
                hopToActivity(Ac_filter.class);
                break;
            case R.id.option_image:
                Bundle bundle = new Bundle();
                bundle.putInt("hop_source", V_Config.CREATE_NEW_RECORD);
                hopToActivity(Ac_record_editor.class, bundle);
                break;
        }
    }
}
