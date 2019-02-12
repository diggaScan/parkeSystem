package com.sunland.xsparkmobile.Bean.i_queryImgInfo;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryImgInfoReq extends RequestBase {

    private String id;//	Long	必填	例如1,2,3

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
