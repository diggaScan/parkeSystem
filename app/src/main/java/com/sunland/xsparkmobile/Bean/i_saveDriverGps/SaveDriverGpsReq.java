package com.sunland.xsparkmobile.Bean.i_saveDriverGps;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class SaveDriverGpsReq extends RequestBase {
    private String tcid;//施救拖车id
    private String sjtchpzl;//号牌种类
    private String sjtchphm;//施救拖车号牌号码

    public String getTcid() {
        return tcid;
    }

    public void setTcid(String tcid) {
        this.tcid = tcid;
    }

    public String getSjtchpzl() {
        return sjtchpzl;
    }

    public void setSjtchpzl(String sjtchpzl) {
        this.sjtchpzl = sjtchpzl;
    }

    public String getSjtchphm() {
        return sjtchphm;
    }

    public void setSjtchphm(String sjtchphm) {
        this.sjtchphm = sjtchphm;
    }
}
