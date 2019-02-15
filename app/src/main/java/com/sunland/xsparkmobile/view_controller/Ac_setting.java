package com.sunland.xsparkmobile.view_controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunland.xsparkmobile.Db.MyDataBase;
import com.sunland.xsparkmobile.Db.OpenRoomDBHelper;
import com.sunland.xsparkmobile.Db.UrlBean;
import com.sunland.xsparkmobile.Db.UrlBeanDao;
import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Rv_Item_decoration;
import com.sunlandgroup.Global;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Ac_setting extends Ac_base implements Frg_add_url.OnUrlDialogClickedListener {

    @BindView(R.id.create_url)
    public TextView tv_create_url;
    @BindView(R.id.url_list)
    public RecyclerView rv_url_list;
    @BindView(R.id.current_url)
    public TextView tv_current_url;


    private MyDataBase myDataBase;
    private MyUrlAdapter myUrlAdapter;
    private List<UrlBean> urlBeanList;

    private UrlBeanDao urlBeanDao;
    private final int SAVE_STATIC_URL = 0;
    private final int CREATE_URL = 1;
    private final int DELETE_URL = 2;
    private final int INITIATE_URLS = 3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CREATE_URL:
                    myUrlAdapter.notifyDataSetChanged();
                    break;
                case DELETE_URL:
                    myUrlAdapter.notifyDataSetChanged();
                    break;
                case INITIATE_URLS:
                    initRv();
                    break;
                case SAVE_STATIC_URL:
                    loadAllUrlBean();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_setting);
        showLightStatusBar();
        showToolBar(true);
        setToolBarTitle("设置");
        tv_option_text.setVisibility(View.VISIBLE);
        tv_option_text.setText("保存");
        myDataBase = OpenRoomDBHelper.createDBHelper(this).getDb();
        urlBeanList = new ArrayList<>();
        saveUrl();
        initView();
    }

    private void saveUrl() {
        urlBeanDao = myDataBase.getUrlBeanDao();
        new Thread(new Runnable() {
            @Override
            public void run() {
                UrlBean urlBean = new UrlBean();
                urlBean.url = Global.ip + ":" + Global.port;
                urlBeanDao.insert(urlBean);
                handler.sendEmptyMessage(SAVE_STATIC_URL);
            }
        }).start();
    }

    private void initView() {
        SharedPreferences sp = getSharedPreferences("cur_url", MODE_PRIVATE);
        String url = sp.getString("url", null);
        if (url != null) {
            Global.ip = url.substring(0, url.lastIndexOf(":"));
            Global.port = url.substring(url.lastIndexOf(":") + 1, url.length());
        }
        tv_current_url.setText(Global.ip + ":" + Global.port);
    }

    @Override
    public void onPositiveClicked(final String ip, final String port, final String desc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UrlBean urlBean = new UrlBean();
                urlBean.url = ip + ":" + port;
                urlBean.desc = desc;
                urlBeanDao.insert(urlBean);
                urlBeanList.clear();
                urlBeanList.addAll(urlBeanDao.getAllUrls());
                handler.sendEmptyMessage(CREATE_URL);
            }
        }).start();
    }

    @OnClick({R.id.create_url, R.id.option_text})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.create_url:
                DialogFragment create_dialog = new Frg_add_url();
                create_dialog.show(getSupportFragmentManager(), "create_url");
                break;
            case R.id.option_text:
                SharedPreferences sp = getSharedPreferences("cur_url", MODE_PRIVATE);
                sp.edit().putString("url", tv_current_url.getText().toString()).apply();
                finish();
                break;
        }
    }

    public void loadAllUrlBean() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<UrlBean> list = urlBeanDao.getAllUrls();
                urlBeanList.clear();
                urlBeanList.addAll(list);
                handler.sendEmptyMessage(INITIATE_URLS);
            }
        }).start();
    }

    private void initRv() {

        myUrlAdapter = new MyUrlAdapter(this, urlBeanList);
        myUrlAdapter.setOnItemClickedListener(new OnItemClickedListener() {
            @Override
            public void onDeleteClicked(final UrlBean urlBean, final int position) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        myDataBase.getUrlBeanDao().delete(urlBean);
                        urlBeanList.remove(urlBean);
                        Message msg = handler.obtainMessage(DELETE_URL);
                        msg.obj = position;
                        handler.sendMessage(msg);
                    }
                }).start();
            }

            @Override
            public void onUseClicked(UrlBean urlBean) {
                tv_current_url.setText(urlBean.url);
                Global.ip = urlBean.url.substring(0, urlBean.url.lastIndexOf(":"));
                Global.port = urlBean.url.substring(urlBean.url.lastIndexOf(":") + 1, urlBean.url.length());
            }
        });
        rv_url_list.setAdapter(myUrlAdapter);
        rv_url_list.setLayoutManager(new LinearLayoutManager(this));
        rv_url_list.addItemDecoration(new Rv_Item_decoration(this));
    }

    private class MyUrlAdapter extends RecyclerView.Adapter<MyUrlAdapter.MyViewHolder> {

        List<UrlBean> dataSet;
        LayoutInflater layoutInflater;
        OnItemClickedListener onItemClickedListener;

        public MyUrlAdapter(Context context, List<UrlBean> dataSet) {
            super();
            this.dataSet = dataSet;
            layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
            this.onItemClickedListener = onItemClickedListener;
        }

        @NonNull
        @Override
        public MyUrlAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = layoutInflater.inflate(R.layout.rv_url_item_view, viewGroup, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyUrlAdapter.MyViewHolder myViewHolder, final int i) {
            final UrlBean urlBean = dataSet.get(i);
            myViewHolder.tv_url.setText(urlBean.url);
            myViewHolder.tv_description.setText(urlBean.desc);
            if (onItemClickedListener != null) {
                myViewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickedListener.onDeleteClicked(urlBean, i);
                    }
                });

                myViewHolder.tv_replace.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickedListener.onUseClicked(urlBean);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return dataSet.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv_url;
            TextView tv_replace;
            TextView tv_delete;
            TextView tv_description;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);
                tv_url = itemView.findViewById(R.id.url);
                tv_replace = itemView.findViewById(R.id.replace);
                tv_delete = itemView.findViewById(R.id.delete);
                tv_description = itemView.findViewById(R.id.description);
            }
        }
    }

    public interface OnItemClickedListener {
        void onDeleteClicked(UrlBean urlBean, int position);

        void onUseClicked(UrlBean urlBean);
    }

}
