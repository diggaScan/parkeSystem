package com.sunland.xsparkmobile.Bean.i_queryCarList;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryCarListReq extends RequestBase {
    private String yjdh;//	String :  移交单号(没有请传空字符“”)
    private String clzt;//	String:车辆状态(没有请传空字符“”)
    private String cphm;//	String : 车牌号码(没有请传空字符“”)
    private String cllx;//	String : 车辆类型(没有请传空字符“”)
    private String tccbh;//	String : 停车场编号(没有请传空字符“”)
    private String stime;//	String :  开始时间(没有请传空字符“”)
    private String etime;//	String : 结束时间(没有请传空字符“”)
    private int startno;//	Int:第几页
    private int rows;//	Int: 一页几条

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

    public String getTccbh() {
        return tccbh;
    }

    public void setTccbh(String tccbh) {
        this.tccbh = tccbh;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public int getStartno() {
        return startno;
    }

    public void setStartno(int startno) {
        this.startno = startno;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
