package com.sunland.xsparkmobile.view_controller.police.Frgs;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Rv_Item_decoration;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.Ac_record_detail;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Car_info_list_adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Frg_search_result extends Fragment {

    @BindView(R.id.car_records_list)
    public RecyclerView rv_car_records;
    private Context mContext;
    private Car_info_list_adapter car_info_list_adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_search_result, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
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
        car_info_list_adapter = new Car_info_list_adapter(mContext, dataSet);

        car_info_list_adapter.setOnItemClickedListener(new Car_info_list_adapter.OnItemClickedListener() {
            @Override
            public void onClick(int position) {
                ((Ac_base) mContext).hopToActivity(Ac_record_detail.class);
            }
        });
        rv_car_records.setAdapter(car_info_list_adapter);
        rv_car_records.setLayoutManager(new LinearLayoutManager(mContext));
        rv_car_records.addItemDecoration(new Rv_Item_decoration(mContext));
    }
}
