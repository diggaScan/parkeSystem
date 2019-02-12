package com.sunland.xsparkmobile.Bean.i_queryCarInfo;

import com.sunland.xsparkmobile.Bean.RequestBase;

import java.util.List;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryCarInfoRes extends RequestBase {

    public List<CarRegoInfo> carRegoInfoList;

    public List<CarRegoInfo> getCarRegoInfoList() {
        return carRegoInfoList;
    }

    public void setCarRegoInfoList(List<CarRegoInfo> carRegoInfoList) {
        this.carRegoInfoList = carRegoInfoList;
    }
}
