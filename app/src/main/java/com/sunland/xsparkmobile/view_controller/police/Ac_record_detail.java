package com.sunland.xsparkmobile.view_controller.police;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Anim.CollapseExpandAnimManager;
import com.sunland.xsparkmobile.Utils.Anim.RotateAnimationManager;
import com.sunland.xsparkmobile.Utils.DialogUtils2;
import com.sunland.xsparkmobile.customView.BannerIndicator;
import com.sunland.xsparkmobile.customView.ZoomImageView;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Img_adapter;

import java.util.ArrayList;
import java.util.List;

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
    public RecyclerView rv_imgs;//车辆照片
    @BindView(R.id.car_status)
    public TextView tv_car_status;
    @BindView(R.id.request_release)
    public TextView tv_request_release;

    private RotateAnimationManager rotateAnimationManager;
    private CollapseExpandAnimManager collapseExpandAnimManager;
    public int hop_src;//跳入该页面的来源

    private boolean hasActionCollapse;
    private boolean hasCarCollapse;
    private boolean hasThroughCollapse;

    private int action_og_height;
    private int car_og_height;
    private int through_og_height;

    private Img_adapter img_adapter;
    private List<Bitmap> bitmapList;

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
        dialogUtils2.initDialog(this);
        rotateAnimationManager = RotateAnimationManager.getInstance();
        collapseExpandAnimManager = CollapseExpandAnimManager.getInstance();
        rotateAnimationManager.initAnim(this, true);
        tv_banner_title.setText("登记详情");
        tv_option_text.setVisibility(View.VISIBLE);
        tv_option_text.setText("转拖车");

        tv_toll_car_position.setText(Html.fromHtml("<u>查看拖车位置</u>"));

        if (tv_car_status.getText().toString().equals("停入")) {
            tv_request_release.setText(Html.fromHtml("<u>申请放行</u>"));
        }
        bitmapList = new ArrayList<>();
        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.center));
        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.left));
        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.right));
        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.rear));
        img_adapter = new Img_adapter(this, bitmapList);
        img_adapter.setOnItemClickedListener(new Img_adapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position, boolean isAdd) {
                showImage(position);
            }
        });
        img_adapter.setEditable(false);
        rv_imgs.setAdapter(img_adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        rv_imgs.setLayoutManager(gridLayoutManager);

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

    @OnClick({R.id.option_text, R.id.through_info, R.id.car_person_info, R.id.action_info, R.id.tow_car_position
            , R.id.request_release})
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
//                    // TODO: 2019/1/28/028  修改
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            img_adapter.notifyDataSetChanged();
//
//                        }
//                    }, 500);
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

            case R.id.request_release:
                dialogUtils2.setTitle("确定提交申请!");
                dialogUtils2.setDescription("");

                dialogUtils2.setOnCancelListener(new DialogUtils2.OnCancelListener() {
                    @Override
                    public void onCancel() {
                        dialogUtils2.dismiss();
                    }
                });

                dialogUtils2.setOnConfirmListener(new DialogUtils2.onConfirmListener() {
                    @Override
                    public void onConfirm() {
                        dialogUtils2.dismiss();
                        hopToActivity(Ac_record_list.class);
                    }
                });
                dialogUtils2.show();
                break;
        }
    }

    private void showImage(final int index) {
        final Dialog dialog = new Dialog(this);
        final Window window = dialog.getWindow();
        LayoutInflater inflater = LayoutInflater.from(this);

        View view = inflater.inflate(R.layout.image_exhibition, null);
        ViewPager viewPager = view.findViewById(R.id.image);
        final TextView tv_index = view.findViewById(R.id.index);
        final List<View> imageViews = new ArrayList<>();
        for (int i = 0; i < bitmapList.size(); i++) {
            ZoomImageView imageView = new ZoomImageView(this);
            imageView.setImageBitmap(bitmapList.get(i));
            imageViews.add(imageView);
        }
        MpageAdapter mpageAdapter = new MpageAdapter(imageViews, dialog);
        viewPager.setAdapter(mpageAdapter);
        viewPager.setCurrentItem(index);
        viewPager.setPageMargin(20);
        final BannerIndicator indicator = view.findViewById(R.id.indicators);
        if (imageViews.size() == 1) {
            indicator.setItem_nums(0);
        } else {
            indicator.setItem_nums(imageViews.size());
        }
        indicator.setMovingDotColor(Color.WHITE);
        indicator.setDotsColor(Color.GRAY);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean start = true;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.setCurrentPosition(position, positionOffset);
                //使用一次该函数来显示index,然后都使用onPageSelected()

                if (start) {
                    tv_index.setText(position + 1 + "/" + imageViews.size());
                    start = false;
                }

            }

            @Override
            public void onPageSelected(int position) {
                tv_index.setText(position + 1 + "/" + imageViews.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setContentView(view);
        window.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
    }

    private class MpageAdapter extends PagerAdapter {

        List<View> dataSet;
        Dialog dialog;

        public MpageAdapter(List<View> dataSet, Dialog dialog) {
            this.dataSet = dataSet;
            this.dialog = dialog;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = dataSet.get(position);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return dataSet.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
