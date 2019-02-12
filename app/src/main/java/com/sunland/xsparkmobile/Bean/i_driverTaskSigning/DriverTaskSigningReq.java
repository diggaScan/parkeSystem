package com.sunland.xsparkmobile.Bean.i_driverTaskSigning;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class DriverTaskSigningReq extends RequestBase {

    private String rwid;//任务id
    private String sjchpzl;//施救拖车号牌种类
    private String sjchphm;// 施救拖车号牌号码

    public String getSjchpzl() {
        return sjchpzl;
    }

    public void setSjchpzl(String sjchpzl) {
        this.sjchpzl = sjchpzl;
    }

    public String getSjchphm() {
        return sjchphm;
    }

    public void setSjchphm(String sjchphm) {
        this.sjchphm = sjchphm;
    }

    public String getRwid() {
        return rwid;
    }

    public void setRwid(String rwid) {
        this.rwid = rwid;
    }
}
