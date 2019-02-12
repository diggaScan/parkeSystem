package com.sunland.xsparkmobile.Bean.i_saveCarPicture;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class Pic {

    private String zplx;//	String 照片类型（01：发动机号照片，02：车架号照片，03：正侧面照片，04：后侧面照片）
    private String zpnr;//	Base64 照片内容

    public String getZplx() {
        return zplx;
    }

    public void setZplx(String zplx) {
        this.zplx = zplx;
    }

    public String getZpnr() {
        return zpnr;
    }

    public void setZpnr(String zpnr) {
        this.zpnr = zpnr;
    }
}
