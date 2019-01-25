package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sunland.xsparkmobile.Bean.CarStatus;
import com.sunland.xsparkmobile.R;

import java.util.List;

public class My_force_certi_adapter extends RecyclerView.Adapter<My_force_certi_adapter.MyViewHolder> {

    private Context context;
    private List<CarStatus> dataSet;
    private LayoutInflater layoutInflater;
    private Resources resources;
    private Car_info_list_adapter.OnItemClickedListener onItemClickedListener;

    public My_force_certi_adapter(Context context) {
        super();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataSet = dataSet;
        this.resources = context.getResources();
    }

    @NonNull
    @Override
    public My_force_certi_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rv_force_certi_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    public void setOnItemClickedListener(Car_info_list_adapter.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    @Override
    public void onBindViewHolder(@NonNull My_force_certi_adapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.rl_content_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout rl_content_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_content_container = itemView.findViewById(R.id.content_container);
        }
    }
}
