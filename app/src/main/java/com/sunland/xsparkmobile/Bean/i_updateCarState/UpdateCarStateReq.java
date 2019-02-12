package com.sunland.xsparkmobile.Bean.i_updateCarState;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class UpdateCarStateReq extends RequestBase {
    public String yjdh;//	String	必填	移交单号
    public String clzt;//	String	必填	车辆状态 如：00、01（见下表备注）{由待拖未签收转为待拖已签收}

    public String getYjdh() {
        return yjdh;
    }

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }
}
