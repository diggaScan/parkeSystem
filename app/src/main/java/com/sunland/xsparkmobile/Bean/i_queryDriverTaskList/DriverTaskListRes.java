package com.sunland.xsparkmobile.Bean.i_queryDriverTaskList;

import com.sunlandgroup.def.bean.result.ResultBase;

import java.util.List;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class DriverTaskListRes extends ResultBase {
    public List<DriverTask> taskList;//司机任务列表

    public List<DriverTask> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<DriverTask> taskList) {
        this.taskList = taskList;
    }
}
