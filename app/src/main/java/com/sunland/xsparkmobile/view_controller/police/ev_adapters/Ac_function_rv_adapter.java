package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunland.xsparkmobile.DataModel;
import com.sunland.xsparkmobile.GlideApp;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.WindowInfoUtils;

public class Ac_function_rv_adapter extends RecyclerView.Adapter<Ac_function_rv_adapter.MyViewHolder> {


    private LayoutInflater layoutInflater;
    private Context context;
    private Resources resource;
    private int item_height;
    private OnItemClickedListener onItemClickedListener;

    public Ac_function_rv_adapter(Context context) {
        super();
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = context.getResources();
        int img_bkg_height = (int) resource.getDimension(R.dimen.img_bkg_height);
        int banner_title_height = (int) resource.getDimension(R.dimen.banner_title_height);
        this.item_height = (int) (WindowInfoUtils.getWindowMetrics(context).heightPixels -img_bkg_height-banner_title_height  )/ 3;

    }

    public void setOnItemClickListener(OnItemClickedListener onItemClickListener) {
        this.onItemClickedListener = onItemClickListener;
    }

    @NonNull
    @Override
    public Ac_function_rv_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.ac_funtion_rv_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ac_function_rv_adapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_name.setText(DataModel.FUNCTION_NAMES[i]);
        GlideApp.with(context).asBitmap().load(DataModel.FUNTION_ICONS[i]).into(myViewHolder.iv_icon);

        myViewHolder.rl_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickedListener != null) {
                    onItemClickedListener.onItemClicked(i);
                }
            }
        });
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, item_height);
        myViewHolder.rl_container.setLayoutParams(lp);
    }

    @Override
    public int getItemCount() {
        return DataModel.FUNCTION_NAMES.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_icon;
        TextView tv_name;
        RelativeLayout rl_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.function_icon);
            tv_name = itemView.findViewById(R.id.functio_name);
            rl_container = itemView.findViewById(R.id.function_container);
        }
    }

    public interface OnItemClickedListener {
        void onItemClicked(int position);
    }
}
