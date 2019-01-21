package com.sunland.xsparkmobile.view_controller.police;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunland.xsparkmobile.DataModel;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.BindView;

public class Ac_filter extends Ac_base {

    @BindView(R.id.status)
    public RecyclerView rv_car_status;
    @BindView(R.id.type)
    public RecyclerView rv_car_type;
    @BindView(R.id.banner_title)
    public TextView tv_banner_title;

    private MyAdapter status_adapter;
    private MyAdapter type_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_filter);
        showToolBar(false);
        tv_banner_title.setText("检索");
        initView();
    }

    private void initView() {
        showLightStatusBar();
        status_adapter = new MyAdapter(DataModel.STATUS);
        type_adapter = new MyAdapter(DataModel.TYPES);
        rv_car_status.setAdapter(status_adapter);
        rv_car_status.setLayoutManager(new GridLayoutManager(this, 2));

        rv_car_type.setAdapter(type_adapter);
        rv_car_type.setLayoutManager(new GridLayoutManager(this, 3));


    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        String[] dataSet;

        public MyAdapter(String[] dataSet) {
            super();
            this.dataSet = dataSet;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.filter_item_layout, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder myViewHolder, int i) {
            myViewHolder.tv_item.setText(dataSet[i]);
        }

        @Override
        public int getItemCount() {
            return dataSet.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_item;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_item = itemView.findViewById(R.id.item);
            }
        }
    }
}
