package com.sunland.xsparkmobile.Bean.i_saveCarInfo;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class Depot {
    private String yjdh;//	String 移交单号
    private String qzcfpz;//	String 强制处罚凭证
    private String kcyy;//	String 扣车原因（1违法2事故3其他）
    private String Kcsj;//Date 扣车时间
    private String dsr;//String 当事人
    private String lxdh;//	String 联系电话
    private String sfzm;//	String 身份证明
    private String lxdz;//	String 联系地址
    private String sjqk;//	String 施救情况(1:拖车拖入，2：代开进入，3：押送进入，4：指定开入)
    private String czqk;//	String 超载情况(1：超载，2：未超载)
    private String xzqk;//	String 卸载情况(1：卸载，2：未卸载)
    private String clwpqd;//	String 车辆物品清单
    private String bgdw;//	String 保管单位（对应停车场表id）
    private String cbmj;//	String 承办民警
    private String cbdw;//	String 承办单位
    private String cbsj;//Date 承办时间
    private String bz;//String 备注
    private String clzt;//	String 车辆类型（1：小轿车，2：半挂货车，3：商务车）
    private String cphm;//	String 车牌号码
    private String hpzl;//	String 号牌种类（0：挂车号牌，1：浙江号牌，2：教练车号牌，3：拖拉机号牌）
    private String fdjhm;//String 发动机号码
    private String cjhm;//String 车架号码
    private String tcdd;//String 拖车地点

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }

    public void setQzcfpz(String qzcfpz) {
        this.qzcfpz = qzcfpz;
    }

    public void setKcyy(String kcyy) {
        this.kcyy = kcyy;
    }

    public void setKcsj(String kcsj) {
        Kcsj = kcsj;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public void setSfzm(String sfzm) {
        this.sfzm = sfzm;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public void setSjqk(String sjqk) {
        this.sjqk = sjqk;
    }

    public void setCzqk(String czqk) {
        this.czqk = czqk;
    }

    public void setXzqk(String xzqk) {
        this.xzqk = xzqk;
    }

    public void setClwpqd(String clwpqd) {
        this.clwpqd = clwpqd;
    }

    public void setBgdw(String bgdw) {
        this.bgdw = bgdw;
    }

    public void setCbmj(String cbmj) {
        this.cbmj = cbmj;
    }

    public void setCbdw(String cbdw) {
        this.cbdw = cbdw;
    }

    public void setCbsj(String cbsj) {
        this.cbsj = cbsj;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setClzt(String clzt) {
        this.clzt = clzt;
    }

    public void setCphm(String cphm) {
        this.cphm = cphm;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public void setFdjhm(String fdjhm) {
        this.fdjhm = fdjhm;
    }

    public void setCjhm(String cjhm) {
        this.cjhm = cjhm;
    }

    public void setTcdd(String tcdd) {
        this.tcdd = tcdd;
    }
}
