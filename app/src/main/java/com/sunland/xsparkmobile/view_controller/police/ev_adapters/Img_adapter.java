package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sunland.xsparkmobile.R;

import java.util.List;

public class Img_adapter extends RecyclerView.Adapter<Img_adapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Bitmap> bitmaps;

    public Img_adapter(Context context, List<Bitmap> bitmaps) {
        super();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bitmaps = bitmaps;
    }

    @NonNull
    @Override
    public Img_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rv_imgs_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Img_adapter.MyViewHolder myViewHolder, int i) {

        if (i == 0) {
            myViewHolder.iv_img.setImageResource(R.drawable.ic_plus);
        }
    }

    @Override
    public int getItemCount() {
        if (bitmaps == null) {
            return 1;
        } else {
            return 1 + bitmaps.size();
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.img);
        }
    }
}
