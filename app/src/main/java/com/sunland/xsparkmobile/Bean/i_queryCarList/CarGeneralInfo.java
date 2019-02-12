package com.sunland.xsparkmobile.Bean.i_queryCarList;

import com.sunlandgroup.def.bean.result.ResultBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class CarGeneralInfo extends ResultBase {
    private String rm;//	第几条数据
    private String yjdh;//	移交单号
    private String cphm;//	车牌号码
    private String cllx;//	车辆类型
    private String dsrdw;//	当事人（单位）
    private String kcsj;//	扣车时间
    private String kcyy;//	扣车原因
    private String clzt;//	车辆状态

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

    public String getYjdh() {
        return yjdh;
    }

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }

    public String getCphm() {
        return cphm;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public String getCllx() {
        return cllx;
    }

    public void setCllx(String cllx) {
        this.cllx = cllx;
    }

    public String getDsrdw() {
        return dsrdw;
    }

    public void setDsrdw(String dsrdw) {
        this.dsrdw = dsrdw;
    }

    public String getKcsj() {
        return kcsj;
    }

    public void setKcsj(String kcsj) {
        this.kcsj = kcsj;
    }

    public String getKcyy() {
        return kcyy;
    }

    public void setKcyy(String kcyy) {
        this.kcyy = kcyy;
    }

    public String getClzt() {
        return clzt;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }
}
