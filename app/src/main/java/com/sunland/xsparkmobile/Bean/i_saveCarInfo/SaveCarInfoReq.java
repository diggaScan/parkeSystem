package com.sunland.xsparkmobile.Bean.i_saveCarInfo;

import com.sunland.xsparkmobile.Bean.RequestBase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class SaveCarInfoReq extends RequestBase {

    private Depot depot;//车辆登记信息对象

    public Depot getDepot() {
        return depot;
    }

    public void setDepot(Depot depot) {
        this.depot = depot;
    }
}
