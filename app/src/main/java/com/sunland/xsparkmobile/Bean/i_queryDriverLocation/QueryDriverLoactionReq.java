package com.sunland.xsparkmobile.Bean.i_queryDriverLocation;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryDriverLoactionReq extends RequestBase {
    private String tcsj;//拖车司机用户id

    public String getTcsj() {
        return tcsj;
    }

    public void setTcsj(String tcsj) {
        this.tcsj = tcsj;
    }
}
