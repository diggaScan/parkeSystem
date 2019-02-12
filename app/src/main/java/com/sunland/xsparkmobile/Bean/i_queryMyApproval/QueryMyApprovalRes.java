package com.sunland.xsparkmobile.Bean.i_queryMyApproval;

import com.sunlandgroup.def.bean.result.ResultBase;

import java.util.List;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryMyApprovalRes extends ResultBase {
    private int page;//	int	总页数
    private List<Spxx> spxx; //审批列表

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Spxx> getSpxx() {
        return spxx;
    }

    public void setSpxx(List<Spxx> spxx) {
        this.spxx = spxx;
    }
}
