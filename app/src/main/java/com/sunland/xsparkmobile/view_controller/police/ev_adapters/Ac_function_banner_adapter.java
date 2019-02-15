package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.BitMapUtils;

import java.util.List;

/**
 * Created By YPT on 2019/2/13/013
 * project name: parkeSystem
 */
public class Ac_function_banner_adapter extends RecyclerView.Adapter<Ac_function_banner_adapter.MyViewHolder> {


    private List<String> dataSet;
    private LayoutInflater layoutInflater;
    private Context context;
    private final int PROFILE = 0;
    private final int NOTIFICATION = 1;

    public Ac_function_banner_adapter(Context context, List<String> dataSet) {
        super();
        this.dataSet = dataSet;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return PROFILE;
        } else {
            return NOTIFICATION;
        }
    }

    @NonNull
    @Override
    public Ac_function_banner_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == PROFILE) {
            view = layoutInflater.inflate(R.layout.banner_user_profile, viewGroup, false);
        } else {
            view = layoutInflater.inflate(R.layout.rv_ac_function_banner, viewGroup, false);
        }
        return new MyViewHolder(view, i);
    }

    @Override
    public void onBindViewHolder(@NonNull Ac_function_banner_adapter.MyViewHolder myViewHolder, int i) {
        if (i == 0) {
            myViewHolder.iv_profile_pic.setImageBitmap(BitMapUtils.circleBitmapByShader(BitmapFactory.decodeResource(context.getResources(), R.drawable.test_police_portrait), 150, 150));
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size() + 1;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;
        TextView tv_title;

        TextView tv_name;
        TextView tv_pid;
        ImageView iv_profile_pic;

        MyViewHolder(@NonNull View itemView, int position) {
            super(itemView);
            if (position == 0) {
                tv_name = itemView.findViewById(R.id.name);
                tv_pid = itemView.findViewById(R.id.pid);
                iv_profile_pic = itemView.findViewById(R.id.profile_pic);
            } else {
                tv_content = itemView.findViewById(R.id.banner_content);
                tv_title = itemView.findViewById(R.id.banner_title);
            }
        }
    }

}
