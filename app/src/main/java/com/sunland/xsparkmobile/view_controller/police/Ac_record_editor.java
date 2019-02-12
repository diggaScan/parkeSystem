package com.sunland.xsparkmobile.view_controller.police;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Anim.CollapseExpandAnimManager;
import com.sunland.xsparkmobile.Utils.Anim.RotateAnimationManager;
import com.sunland.xsparkmobile.Utils.DialogUtils;
import com.sunland.xsparkmobile.V_Config;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.parkManager.Ac_park_funciton;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Img_adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_record_editor extends Ac_base {


    @BindView(R.id.scrollView)
    public ScrollView scrollView;
    @BindView(R.id.details)
    public LinearLayout ll_details;

    @BindView(R.id.action_info_arrow)
    public ImageView iv_action_info_arrow;
    @BindView(R.id.car_person_info_arrow)
    public ImageView iv_car_person_info_arrow;
    @BindView(R.id.through_info_arrow)
    public ImageView iv_through_info_arrow;

    @BindView(R.id.action_detail)
    public RelativeLayout rl_action_detail;
    @BindView(R.id.implementee_info)
    public RelativeLayout rl_car_detail;
    @BindView(R.id.police_info)
    public RelativeLayout rl_police_info;

    @BindView(R.id.imgs)
    public RecyclerView rv_imgs;//车辆照片


    private int action_og_height;
    private int car_og_height;
    private int through_og_height;
    private boolean hasActionCollapse;
    private boolean hasCarCollapse;
    private boolean hasThroughCollapse;

    private int hop_source;

    private CollapseExpandAnimManager collapseExpandAnimManager;
    private RotateAnimationManager rotateAnimationManager;

    private List<Bitmap> bitmapList;
    private Img_adapter img_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_record_editor);
        showToolBar(true);
        showLightStatusBar();
        collapseExpandAnimManager = CollapseExpandAnimManager.getInstance();
        rotateAnimationManager = RotateAnimationManager.getInstance();
        handleIntent();
        initView();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                hop_source = bundle.getInt("hop_source", -1);
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (isInit) {
            action_og_height = rl_action_detail.getHeight();
            car_og_height = rl_car_detail.getHeight();
            through_og_height = rl_police_info.getHeight();
            collapseExpandAnimManager.initTarget(rl_action_detail, action_og_height, null, null);
            collapseExpandAnimManager.initTarget(rl_car_detail, car_og_height, null, null);
            collapseExpandAnimManager.initTarget(rl_police_info, through_og_height, new CollapseExpandAnimManager.OnAnimationExpandListener() {
                @Override
                public void onAnimationExpand() {
                    scrollView.scrollTo(0, ll_details.getHeight());
                }
            }, null);
            isInit = false;
        }
    }

    private void initView() {
        if (hop_source == V_Config.CREATE_NEW_RECORD) {
            setToolBarTitle("信息录入");

        } else if (hop_source == V_Config.SUPPLEMENT_RECORD) {
            setToolBarTitle("信息补录");
        } else {
            setToolBarTitle("信息补录");
        }
        bitmapList = new ArrayList<>();
        img_adapter = new Img_adapter(this, bitmapList);
        img_adapter.setOnItemDeleteClickedListener(new Img_adapter.OnItemDeleteClickedListener() {
            @Override
            public void onDelete(int position) {
                bitmapList.remove(position - 1);
                img_adapter.notifyItemRemoved(position);
            }
        });
        img_adapter.setOnItemClickedListener(new Img_adapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position, boolean isAdd) {
                if (isAdd) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, V_Config.REQUEST_IMG);
                    }
                }
            }
        });

        rv_imgs.setAdapter(img_adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rv_imgs.setLayoutManager(gridLayoutManager);
    }

    @OnClick({R.id.submit, R.id.action_cate_title, R.id.car_cate_title, R.id.police_cate_title})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.submit:
                final Handler handler = new Handler();
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        dialogUtils.loadSuccess("保存数据成功", false);
                    }
                };
                handler.postDelayed(runnable, 1000);

                dialogUtils.showDialog(this, "补录数据中...", DialogUtils.TYPE_PROGRESS, new DialogUtils.OnCancelClickListener() {
                    @Override
                    public void onCancel() {
                        dialogUtils.dialogDismiss();
                        if (hop_source == V_Config.ACTIVITY_PARK_FUNCTION) {
                            hopToActivity(Ac_park_funciton.class);
                        } else {
                            hopToActivity(Ac_record_list.class);
                        }
                    }
                }, null);

                break;
            case R.id.action_cate_title:
                if (hasActionCollapse) {
                    rotateAnimationManager.start(iv_action_info_arrow, RotateAnimationManager.EXPAND);
                    collapseExpandAnimManager.start(rl_action_detail, CollapseExpandAnimManager.EXPAND);
                    hasActionCollapse = false;
                } else {
                    rotateAnimationManager.start(iv_action_info_arrow, RotateAnimationManager.COLLAPSE);
                    collapseExpandAnimManager.start(rl_action_detail, CollapseExpandAnimManager.COLLAPSE);
                    hasActionCollapse = true;
                }
                break;
            case R.id.car_cate_title:
                if (hasCarCollapse) {
                    rotateAnimationManager.start(iv_car_person_info_arrow, RotateAnimationManager.EXPAND);
                    collapseExpandAnimManager.start(rl_car_detail, CollapseExpandAnimManager.EXPAND);
                    hasCarCollapse = false;
                } else {
                    img_adapter.clearStatus();
                    rotateAnimationManager.start(iv_car_person_info_arrow, RotateAnimationManager.COLLAPSE);
                    collapseExpandAnimManager.start(rl_car_detail, CollapseExpandAnimManager.COLLAPSE);
                    hasCarCollapse = true;
                }
                break;
            case R.id.police_cate_title:
                if (hasThroughCollapse) {
                    rotateAnimationManager.start(iv_through_info_arrow, RotateAnimationManager.EXPAND);
                    collapseExpandAnimManager.start(rl_police_info, CollapseExpandAnimManager.EXPAND);
                    hasThroughCollapse = false;
                } else {
                    rotateAnimationManager.start(iv_through_info_arrow, RotateAnimationManager.COLLAPSE);
                    collapseExpandAnimManager.start(rl_police_info, CollapseExpandAnimManager.COLLAPSE);
                    hasThroughCollapse = true;
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == V_Config.REQUEST_IMG) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");
                bitmapList.add(bitmap);
                img_adapter.notifyItemChanged(bitmapList.size());
                rv_imgs.requestFocus();
            }
        }
    }
}
