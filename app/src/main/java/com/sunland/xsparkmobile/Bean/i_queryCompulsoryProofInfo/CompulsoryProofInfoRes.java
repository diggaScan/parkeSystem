package com.sunland.xsparkmobile.Bean.i_queryCompulsoryProofInfo;

import com.sunlandgroup.def.bean.result.ResultBase;

import java.util.List;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class CompulsoryProofInfoRes extends ResultBase {

    private int totalCount;//	int 总条数
    private int totalPage;//	int 总页数
    private List<Qzcfpz> qzcfpzList;//	强制处罚凭证列表

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Qzcfpz> getQzcfpzList() {
        return qzcfpzList;
    }

    public void setQzcfpzList(List<Qzcfpz> qzcfpzList) {
        this.qzcfpzList = qzcfpzList;
    }
}
