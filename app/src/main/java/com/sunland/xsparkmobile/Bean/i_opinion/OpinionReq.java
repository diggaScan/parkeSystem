package com.sunland.xsparkmobile.Bean.i_opinion;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class OpinionReq extends RequestBase {
    private String spid;//	String	必填	审批表id
    private String shbm;//	String	必填	用户部门代码
    private String sftg;//	String	必填	是否通过：0不通过1通过
    private String sqhzt;//	String	必填	审批后状态
    private String bz;//	String		备注
    private String yjdh;//	String	必填	移交单号id

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getShbm() {
        return shbm;
    }

    public void setShbm(String shbm) {
        this.shbm = shbm;
    }

    public String getSftg() {
        return sftg;
    }

    public void setSftg(String sftg) {
        this.sftg = sftg;
    }

    public String getSqhzt() {
        return sqhzt;
    }

    public void setSqhzt(String sqhzt) {
        this.sqhzt = sqhzt;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getYjdh() {
        return yjdh;
    }

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }
}
