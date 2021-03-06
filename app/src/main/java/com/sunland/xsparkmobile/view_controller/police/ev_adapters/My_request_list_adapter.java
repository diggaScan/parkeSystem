package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sunland.xsparkmobile.Bean.Request;
import com.sunland.xsparkmobile.R;

import java.util.List;

public class My_request_list_adapter extends RecyclerView.Adapter<My_request_list_adapter.MyViewholder> {


    private List<Request> dataSet;
    private Context context;
    private LayoutInflater layoutInflater;
    private Car_info_list_adapter.OnItemClickedListener onItemClickedListener;

    public My_request_list_adapter(Context context, List<Request> dataSet) {
        super();
        this.dataSet = dataSet;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickedListener(Car_info_list_adapter.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    @NonNull
    @Override
    public My_request_list_adapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rv_request_list_item, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_request_list_adapter.MyViewholder myViewholder, final int i) {
        myViewholder.rl_content_detail.setOnClickListener(new View.OnClickListener() {
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

    class MyViewholder extends RecyclerView.ViewHolder {

        RelativeLayout rl_content_detail;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            rl_content_detail = itemView.findViewById(R.id.content_detail);
        }
    }


}
