package com.sunland.xsparkmobile.view_controller.police.ev_adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.sunland.xsparkmobile.GlideApp;
import com.sunland.xsparkmobile.R;

import java.util.List;

public class Img_adapter extends RecyclerView.Adapter<Img_adapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Bitmap> bitmaps;
    private boolean editable = true;//默认可编辑
    private OnItemClickedListener onItemClickedListener;
    private OnItemDeleteClickedListener onItemDeleteClickedListener;
    private boolean isReadyDelete;
    private Animation animation;

    public Img_adapter(Context context, List<Bitmap> bitmaps) {
        super();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bitmaps = bitmaps;
        animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.pic_delete_ready_shimmy);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public void clearStatus() {
        isReadyDelete = false;
        this.notifyDataSetChanged();
    }

    public void setOnItemDeleteClickedListener(OnItemDeleteClickedListener onItemDeleteClickedListener) {
        this.onItemDeleteClickedListener = onItemDeleteClickedListener;
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    @NonNull
    @Override
    public Img_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.rv_imgs_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Img_adapter.MyViewHolder myViewHolder, final int i) {

        if (!isReadyDelete || (editable && i == 0)) {
            myViewHolder.fl_pic_container.clearAnimation();
            myViewHolder.iv_delete.setVisibility(View.GONE);
        } else {
            myViewHolder.fl_pic_container.startAnimation(animation);
            myViewHolder.iv_delete.setVisibility(View.VISIBLE);
            myViewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemDeleteClickedListener != null) {
                        onItemDeleteClickedListener.onDelete(i);
                    }
                }
            });
        }
        if (editable) {
            if (i == 0) {
                myViewHolder.iv_img.setScaleType(ImageView.ScaleType.CENTER);
                myViewHolder.iv_img.setImageResource(R.drawable.ic_plus);
                myViewHolder.iv_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(i, true);
                        }
                    }
                });
            } else {
                GlideApp.with(context).asBitmap().load(bitmaps.get(i - 1)).into(myViewHolder.iv_img);
                myViewHolder.iv_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isReadyDelete) {
                            isReadyDelete = false;
                            Img_adapter.this.notifyDataSetChanged();
                        }
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(i, false);
                        }
                    }
                });
                myViewHolder.iv_img.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (isReadyDelete) {
                            return false;
                        }
                        isReadyDelete = true;
                        Img_adapter.this.notifyDataSetChanged();
                        return false;
                    }
                });
//                myViewHolder.iv_img.setImageBitmap(bitmap);
            }
        } else {
            GlideApp.with(context).asBitmap().load(bitmaps.get(i)).into(myViewHolder.iv_img);
            myViewHolder.iv_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isReadyDelete) {
                        isReadyDelete = false;
                        Img_adapter.this.notifyDataSetChanged();
                    }
                    if (onItemClickedListener != null) {
                        onItemClickedListener.onItemClicked(i, false);
                    }
                }
            });
//            myViewHolder.iv_img.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        if (editable) {
            if (bitmaps == null || bitmaps.isEmpty()) {
                return 1;
            } else {
                return 1 + bitmaps.size();
            }
        } else {
            return bitmaps.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        ImageView iv_delete;
        FrameLayout fl_pic_container;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.img);
            iv_delete = itemView.findViewById(R.id.delete);
            fl_pic_container = itemView.findViewById(R.id.pic_container);
        }
    }

    public interface OnItemClickedListener {
        void onItemClicked(int position, boolean isAdd);
    }

    public interface OnItemDeleteClickedListener {
        void onDelete(int position);
    }
}
