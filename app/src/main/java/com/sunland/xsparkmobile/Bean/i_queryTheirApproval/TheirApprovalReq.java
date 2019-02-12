package com.sunland.xsparkmobile.Bean.i_queryTheirApproval;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class TheirApprovalReq extends RequestBase {
    private String lx;//	String	1审批中2审批通过，3审批驳回，空全查询
    private int startno;//int	第几页
    private int rows;//	int	一页几条

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
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
