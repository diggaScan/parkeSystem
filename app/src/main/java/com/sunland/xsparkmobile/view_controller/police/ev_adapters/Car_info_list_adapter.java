package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunland.xsparkmobile.Bean.CarStatus;
import com.sunland.xsparkmobile.DataModel;
import com.sunland.xsparkmobile.R;

import java.util.List;

public class Car_info_list_adapter extends RecyclerView.Adapter<Car_info_list_adapter.MyViewholder> {

    private Context context;
    private List<CarStatus> dataSet;
    private LayoutInflater layoutInflater;
    private Resources resources;

    public Car_info_list_adapter(Context context, List<CarStatus> dataSet) {
        super();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataSet = dataSet;
        this.resources = context.getResources();
    }

    @NonNull
    @Override
    public Car_info_list_adapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rv_car_info_list_item, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Car_info_list_adapter.MyViewholder myViewholder, int i) {
        int status = dataSet.get(i).getStatus();
        myViewholder.tv_status.setText(DataModel.STATUS[status]);
        myViewholder.tv_status.setTextColor(resources.getColor(DataModel.STATUS_COLORS[status]));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class MyViewholder extends RecyclerView.ViewHolder {
        TextView tv_status;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tv_status = itemView.findViewById(R.id.status);
        }
    }

    public interface OnItemClickedListener {
        void onClick(int position);
    }
}
