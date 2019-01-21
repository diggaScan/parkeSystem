package com.sunland.xsparkmobile.view_controller.police;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunland.xsparkmobile.R;

public class Car_info_list_adapter extends RecyclerView.Adapter<Car_info_list_adapter.MyViewholder> {

    private Context context;
    private LayoutInflater layoutInflater;

    public Car_info_list_adapter(Context context) {
        super();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public Car_info_list_adapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.car_info_list_item, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Car_info_list_adapter.MyViewholder myViewholder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
