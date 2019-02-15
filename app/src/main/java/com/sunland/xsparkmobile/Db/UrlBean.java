package com.sunland.xsparkmobile.Db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
@Entity(tableName = "t_urls")
public class UrlBean {

    @PrimaryKey
    @NonNull
    public String url;


    public String desc;


}
