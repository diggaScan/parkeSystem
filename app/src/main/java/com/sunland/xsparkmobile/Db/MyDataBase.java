package com.sunland.xsparkmobile.Db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
@Database(entities = {UrlBean.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {

    public abstract UrlBeanDao getUrlBeanDao();

}
