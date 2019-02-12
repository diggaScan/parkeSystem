package com.sunland.xsparkmobile.Bean.i_queryImgInfo;

import com.sunlandgroup.def.bean.result.ResultBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryImgInfoRes extends ResultBase {

    private String zp;//	String	照片（BASE64 String）

    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }
}
