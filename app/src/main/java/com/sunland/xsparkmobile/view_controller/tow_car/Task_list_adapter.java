package com.sunland.xsparkmobile.view_controller.tow_car;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Car_info_list_adapter;

public class Task_list_adapter extends RecyclerView.Adapter<Task_list_adapter.MyViewHolder> {

    private Context context;

    private LayoutInflater layoutInflater;
    private Resources resources;
    private Car_info_list_adapter.OnItemClickedListener onItemClickedListener;

    public Task_list_adapter(Context context) {
        super();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resources = context.getResources();
    }

    public void setOnItemClickedListener(Car_info_list_adapter.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    @NonNull
    @Override
    public Task_list_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rv_tow_car_task_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Task_list_adapter.MyViewHolder myViewHolder,final int i) {
        myViewHolder.tv_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickedListener!=null){
                    onItemClickedListener.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_accept;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_accept = itemView.findViewById(R.id.accept);
        }
    }
}
