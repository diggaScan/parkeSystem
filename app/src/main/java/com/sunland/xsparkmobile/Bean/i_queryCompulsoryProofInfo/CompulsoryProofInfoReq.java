package com.sunland.xsparkmobile.Bean.i_queryCompulsoryProofInfo;

import com.sunland.xsparkmobile.Bean.RequestBase;

public class CompulsoryProofInfoReq extends RequestBase {
    private String hphm;//号牌号码
    private String hpzl;//号牌种类
    private String qzcspzh;//强制措施凭证号
    private int pageNo;//每页显示条数
    private int pageIndex;//分页索引(第几页)

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl;
    }

    public String getQzcspzh() {
        return qzcspzh;
    }

    public void setQzcspzh(String qzcspzh) {
        this.qzcspzh = qzcspzh;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
