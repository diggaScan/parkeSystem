package com.sunland.xsparkmobile.view_controller.police;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Anim.CollapseExpandAnimManager;
import com.sunland.xsparkmobile.Utils.Anim.RotateAnimationManager;
import com.sunland.xsparkmobile.V_Config;
import com.sunland.xsparkmobile.view_controller.Ac_base;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_record_detail extends Ac_base {

    @BindView(R.id.banner_title)
    public TextView tv_banner_title;
    @BindView(R.id.scrollView)
    public ScrollView sv_container;

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

    @BindView(R.id.tow_car_position)
    public TextView tv_toll_car_position;

    @BindView(R.id.imgs)
    public RecyclerView rv_imgs;

    private RotateAnimationManager rotateAnimationManager;
    private CollapseExpandAnimManager collapseExpandAnimManager;
    public int hop_src;//跳入该页面的来源

    private boolean hasActionCollapse;
    private boolean hasCarCollapse;
    private boolean hasThroughCollapse;

    private int action_og_height;
    private int car_og_height;
    private int through_og_height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_record_detail);
        handleIntent();
        showToolBar(true);
        showLightStatusBar();
        initView();
    }

    public void initView() {
        rotateAnimationManager = RotateAnimationManager.getInstance();
        collapseExpandAnimManager = CollapseExpandAnimManager.getInstance();
        rotateAnimationManager.initAnim(this, true);
        tv_banner_title.setText("登记详情");
        tv_option_text.setVisibility(View.VISIBLE);
        if (hop_src == V_Config.ACTIVITY_PARK_FUNCTION) {
            tv_option_text.setText("转拖车");
        } else {
            tv_option_text.setText("编辑");
        }

        tv_toll_car_position.setText(Html.fromHtml("<u>查看拖车位置</u>"));
    }

    public void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                hop_src = bundle.getInt("hop_source");
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
                    sv_container.scrollTo(0, ll_details.getHeight());
                }
            }, null);
            isInit = false;
        }
    }

    @OnClick({R.id.option_text, R.id.through_info, R.id.car_person_info, R.id.action_info, R.id.tow_car_position})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.option_text:
                Bundle bundle = new Bundle();
                bundle.putInt("hop_source", hop_src);
                hopToActivity(Ac_record_editor.class, bundle);
                break;
            case R.id.action_info:
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
            case R.id.car_person_info:
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
            case R.id.through_info:
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

            case R.id.tow_car_position:
                hopToActivity(Ac_tow_car_position.class);
                break;
        }
    }
}
