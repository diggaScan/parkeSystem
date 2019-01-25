package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sunland.xsparkmobile.R;

public class My_approval_list_adapter extends RecyclerView.Adapter<My_approval_list_adapter.MyViewHolder> {


    private Context context;
    private LayoutInflater layoutInflater;

    private Car_info_list_adapter.OnItemClickedListener onItemClickedListener;

    public My_approval_list_adapter(Context context) {
        super();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setOnItemClickedListener(Car_info_list_adapter.OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    @NonNull
    @Override
    public My_approval_list_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rv_my_approval_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My_approval_list_adapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.rl_content_detail.setOnClickListener(new View.OnClickListener() {
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
        RelativeLayout rl_content_detail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rl_content_detail = itemView.findViewById(R.id.content_detail);
        }
    }


}
