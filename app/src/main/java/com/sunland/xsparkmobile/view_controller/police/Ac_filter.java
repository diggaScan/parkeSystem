package com.sunland.xsparkmobile.view_controller.police;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunland.xsparkmobile.DataModel;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.V_Config;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_filter extends Ac_base {

    @BindView(R.id.status)
    public RecyclerView rv_car_status;
    @BindView(R.id.type)
    public RecyclerView rv_car_type;

    private MyAdapter status_adapter;
    private MyAdapter type_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_filter);
        showToolBar(true);
        setToolBarTitle("检索");
        initView();
    }

    private void initView() {
        showLightStatusBar();
        status_adapter = new MyAdapter(this, DataModel.STATUS);
        status_adapter.setColor_type(V_Config.GOOGLE_COLOR_ONE);
        type_adapter = new MyAdapter(this, DataModel.TYPES);
        type_adapter.setColor_type(V_Config.GOOGLE_COLOR_TWO);
        rv_car_status.setAdapter(status_adapter);
        rv_car_status.setLayoutManager(new GridLayoutManager(this, 2));

        rv_car_type.setAdapter(type_adapter);
        rv_car_type.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @OnClick(R.id.enter_btn)
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.enter_btn:
                finish();
                break;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        String[] dataSet;
        private Context context;
        private int color_type;
        private Resources resources;
        private TextView cur_chosen_item;

        public MyAdapter(Context context, String[] dataSet) {
            super();
            this.dataSet = dataSet;
            this.context = context;
            this.resources = context.getResources();
        }

        public void setColor_type(int color_type) {
            this.color_type = color_type;
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.filter_item_layout, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder myViewHolder, int i) {
            myViewHolder.tv_item.setText(dataSet[i]);
            switch (color_type) {
                case V_Config.GOOGLE_COLOR_ONE:
                    if (i == 0) {
                        cur_chosen_item = myViewHolder.tv_item;
                        myViewHolder.tv_item.setTextColor(resources.getColor(R.color.google_color_one));
                        myViewHolder.tv_item.setBackgroundDrawable(resources.getDrawable(R.drawable.filter_item_bkg_one));
                    }
                    myViewHolder.tv_item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cur_chosen_item.setTextColor(resources.getColor(R.color.black));
                            cur_chosen_item.setBackgroundDrawable(resources.getDrawable(R.drawable.filter_item_bkg));
                            myViewHolder.tv_item.setTextColor(resources.getColor(R.color.google_color_one));
                            myViewHolder.tv_item.setBackgroundDrawable(resources.getDrawable(R.drawable.filter_item_bkg_one));
                            cur_chosen_item = myViewHolder.tv_item;

                        }
                    });

                    break;
                case V_Config.GOOGLE_COLOR_TWO:
                    if (i == 0) {
                        cur_chosen_item = myViewHolder.tv_item;
                        myViewHolder.tv_item.setTextColor(resources.getColor(R.color.google_color_two));
                        myViewHolder.tv_item.setBackgroundDrawable(resources.getDrawable(R.drawable.filter_item_bkg_two));
                    }
                    myViewHolder.tv_item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cur_chosen_item.setTextColor(resources.getColor(R.color.black));
                            cur_chosen_item.setBackgroundDrawable(resources.getDrawable(R.drawable.filter_item_bkg));
                            myViewHolder.tv_item.setTextColor(resources.getColor(R.color.google_color_two));
                            myViewHolder.tv_item.setBackgroundDrawable(resources.getDrawable(R.drawable.filter_item_bkg_two));
                            cur_chosen_item = myViewHolder.tv_item;
                        }
                    });
                    break;
            }
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
