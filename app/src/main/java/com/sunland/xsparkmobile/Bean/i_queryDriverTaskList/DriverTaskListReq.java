package com.sunland.xsparkmobile.Bean.i_queryDriverTaskList;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class DriverTaskListReq extends RequestBase {
    public String qszt;//签收状态

    public String getQszt() {
        return qszt;
    }

    public void setQszt(String qszt) {
        this.qszt = qszt;
    }
}
