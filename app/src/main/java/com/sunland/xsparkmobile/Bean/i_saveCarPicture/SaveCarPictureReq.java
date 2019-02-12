package com.sunland.xsparkmobile.Bean.i_saveCarPicture;

import com.sunland.xsparkmobile.Bean.RequestBase;

import java.util.List;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
public class SaveCarPictureReq extends RequestBase {
    private String yjdh;// 移交单号
    private List<Pic> picturelist;//照片对象列表

    public String getYjdh() {
        return yjdh;
    }

    public void setYjdh(String yjdh) {
        this.yjdh = yjdh;
    }

    public List<Pic> getPicturelist() {
        return picturelist;
    }

    public void setPicturelist(List<Pic> picturelist) {
        this.picturelist = picturelist;
    }
}
