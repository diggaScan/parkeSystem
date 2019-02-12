package com.sunland.xsparkmobile.Bean.i_queryCarList;

import com.sunlandgroup.def.bean.result.ResultBase;

import java.util.List;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class QueryCarListRes extends ResultBase {
    private List<CarGeneralInfo> carGeneralInfoList;

    public List<CarGeneralInfo> getCarGeneralInfoList() {
        return carGeneralInfoList;
    }

    public void setCarGeneralInfoList(List<CarGeneralInfo> carGeneralInfoList) {
        this.carGeneralInfoList = carGeneralInfoList;
    }
}
