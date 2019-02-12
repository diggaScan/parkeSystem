package com.sunland.xsparkmobile.Bean.i_queryMyApproval;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryMyApprovalReq extends RequestBase {

    private String lx;//	String		1审批中0审批完成，空全查询
    private int startno;//	int	必填	第几页
    private int rows;//	int	必填	一页几条

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
