package com.sunland.xsparkmobile.Bean.i_queryDriverTaskList;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class DriverTask {

    private String id;//	String 任务列表id
    private String yjdh;//	String 移交单号
    private String czr;//String 操作人
    private String czbm;//	String 操作人部门
    private String czsj;//	Date 操作时间（指派时间）
    private String tcsj;//	String 拖车司机
    private String sjchpzl;//	String 施救拖车号牌种类
    private String sjchphm;//	String 施救拖车号牌号码
    private String qszt;//	String 签收状态（0未签收，1：已签收）
    private String tcdd;//	String 拖车地点
    private String tcdjd;//	String 拖车地经度
    private String tcdwd;//	String 拖车地纬度

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYjdh() {
        return yjdh;
    }

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }

    public String getCzr() {
        return czr;
    }

    public void setCzr(String czr) {
        this.czr = czr;
    }

    public String getCzbm() {
        return czbm;
    }

    public void setCzbm(String czbm) {
        this.czbm = czbm;
    }

    public String getCzsj() {
        return czsj;
    }

    public void setCzsj(String czsj) {
        this.czsj = czsj;
    }

    public String getTcsj() {
        return tcsj;
    }

    public void setTcsj(String tcsj) {
        this.tcsj = tcsj;
    }

    public String getSjchpzl() {
        return sjchpzl;
    }

    public void setSjchpzl(String sjchpzl) {
        this.sjchpzl = sjchpzl;
    }

    public String getSjchphm() {
        return sjchphm;
    }

    public void setSjchphm(String sjchphm) {
        this.sjchphm = sjchphm;
    }

    public String getQszt() {
        return qszt;
    }

    public void setQszt(String qszt) {
        this.qszt = qszt;
    }

    public String getTcdd() {
        return tcdd;
    }

    public void setTcdd(String tcdd) {
        this.tcdd = tcdd;
    }

    public String getTcdjd() {
        return tcdjd;
    }

    public void setTcdjd(String tcdjd) {
        this.tcdjd = tcdjd;
    }

    public String getTcdwd() {
        return tcdwd;
    }

    public void setTcdwd(String tcdwd) {
        this.tcdwd = tcdwd;
    }
}
