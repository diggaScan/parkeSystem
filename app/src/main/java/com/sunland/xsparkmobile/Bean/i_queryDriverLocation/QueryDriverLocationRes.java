package com.sunland.xsparkmobile.Bean.i_queryDriverLocation;

import com.sunlandgroup.def.bean.result.ResultBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryDriverLocationRes extends ResultBase {
    private String tcsjxm;//	String	必填	拖车司机姓名
    private String lxfs;//	String	必填	拖车司机联系方式
    private String sjchphm;//	String	必填	施救拖车号牌号码
    private String sstcc;//	String	必填	所属停车场
    private String jd;//	String	必填	上传地经度
    private String wd;//	String	必填	上传地纬度

    public String getTcsjxm() {
        return tcsjxm;
    }

    public void setTcsjxm(String tcsjxm) {
        this.tcsjxm = tcsjxm;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getSjchphm() {
        return sjchphm;
    }

    public void setSjchphm(String sjchphm) {
        this.sjchphm = sjchphm;
    }

    public String getSstcc() {
        return sstcc;
    }

    public void setSstcc(String sstcc) {
        this.sstcc = sstcc;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }
}
