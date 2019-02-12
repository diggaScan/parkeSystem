package com.sunland.xsparkmobile.Bean.i_queryCarInfo;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryCarInfoReq extends RequestBase {
    private String yjdh;//	String	必填	移交单号

    public String getYjdh() {
        return yjdh;
    }

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }
}
