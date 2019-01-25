package com.sunland.xsparkmobile.view_controller.tow_car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sunland.xsparkmobile.R;
import com.sunland.xsparkmobile.Utils.Rv_Item_decoration;
import com.sunland.xsparkmobile.view_controller.Ac_base;
import com.sunland.xsparkmobile.view_controller.police.ev_adapters.Car_info_list_adapter;

import butterknife.BindView;

public class Ac_task_list extends Ac_base {

    @BindView(R.id.task_list)
    public RecyclerView rv_task_list;



    private Task_list_adapter task_list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_task_list);
        showLightStatusBar();
        initView();
    }

    private void initView() {
        setToolBarTitle("任务列表");
        task_list_adapter = new Task_list_adapter(this);
        task_list_adapter.setOnItemClickedListener(new Car_info_list_adapter.OnItemClickedListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent();
                intent.putExtra(Constants.KEY_WORDS_NAME, "浙江国贸大厦");
                setResult(Constants.RESULT_OF_KEYWORD, intent);
                finish();
            }
        });
        rv_task_list.setAdapter(task_list_adapter);
        rv_task_list.setLayoutManager(new LinearLayoutManager(this));
        rv_task_list.addItemDecoration(new Rv_Item_decoration(this));
    }


}
