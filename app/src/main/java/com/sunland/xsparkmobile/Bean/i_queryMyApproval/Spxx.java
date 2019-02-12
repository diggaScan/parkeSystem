package com.sunland.xsparkmobile.Bean.i_queryMyApproval;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class Spxx {
    private String id;//	String	审批表id
    private String depotid;//	String	车辆登记id
    private String sftg;//	String	空值为审核中0不通过，1通过
    private String sqqzt;//	String	申请前状态
    private String sqhzt;//	String	申请后状态
    private String sqr;//	String	申请人
    private String sqbm;//	String	申请部门代码
    private String sqbmstr;//	String	申请部门名称
    private String sqsj;//	date	申请时间
    private String shry;//	String	审核人
    private String shbm;//	String	审核部门代码
    private String shbmstr;//	String	审核部门名称
    private String shsj;//	date	审核时间
    private String bz;//	String	备注
    private String czlx;//	String	操作类型 1修改数据2违法3事故
    private String zt;//	String	数据状态：0无效1有效
    private ApprovalCarInfo depot;//	depot	车辆登记信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepotid() {
        return depotid;
    }

    public void setDepotid(String depotid) {
        this.depotid = depotid;
    }

    public String getSftg() {
        return sftg;
    }

    public void setSftg(String sftg) {
        this.sftg = sftg;
    }

    public String getSqqzt() {
        return sqqzt;
    }

    public void setSqqzt(String sqqzt) {
        this.sqqzt = sqqzt;
    }

    public String getSqhzt() {
        return sqhzt;
    }

    public void setSqhzt(String sqhzt) {
        this.sqhzt = sqhzt;
    }

    public String getSqr() {
        return sqr;
    }

    public void setSqr(String sqr) {
        this.sqr = sqr;
    }

    public String getSqbm() {
        return sqbm;
    }

    public void setSqbm(String sqbm) {
        this.sqbm = sqbm;
    }

    public String getSqbmstr() {
        return sqbmstr;
    }

    public void setSqbmstr(String sqbmstr) {
        this.sqbmstr = sqbmstr;
    }

    public String getSqsj() {
        return sqsj;
    }

    public void setSqsj(String sqsj) {
        this.sqsj = sqsj;
    }

    public String getShry() {
        return shry;
    }

    public void setShry(String shry) {
        this.shry = shry;
    }

    public String getShbm() {
        return shbm;
    }

    public void setShbm(String shbm) {
        this.shbm = shbm;
    }

    public String getShbmstr() {
        return shbmstr;
    }

    public void setShbmstr(String shbmstr) {
        this.shbmstr = shbmstr;
    }

    public String getShsj() {
        return shsj;
    }

    public void setShsj(String shsj) {
        this.shsj = shsj;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getCzlx() {
        return czlx;
    }

    public void setCzlx(String czlx) {
        this.czlx = czlx;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public ApprovalCarInfo getDepot() {
        return depot;
    }

    public void setDepot(ApprovalCarInfo depot) {
        this.depot = depot;
    }
}
