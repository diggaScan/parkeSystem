package com.sunland.xsparkmobile.view_controller.police;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
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
    private Img_adapter img_adapter;

    private int action_og_height;
    private int car_og_height;
    private int through_og_height;
    private boolean hasActionCollapse;
    private boolean hasCarCollapse;
    private boolean hasThroughCollapse;

    private int hop_source;

    private CollapseExpandAnimManager collapseExpandAnimManager;
    private RotateAnimationManager rotateAnimationManager;

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
        }
        img_adapter=new Img_adapter(this,null);
        rv_imgs.setAdapter(img_adapter);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_imgs.setLayoutManager(llm);

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
}
