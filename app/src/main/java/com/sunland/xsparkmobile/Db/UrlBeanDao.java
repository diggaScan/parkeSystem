package com.sunland.xsparkmobile.Db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created By YPT on 2019/2/12/012
 * project name: parkeSystem
 */
@Dao
public interface UrlBeanDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(UrlBean urlbean);

    @Delete
    public void delete(UrlBean urlbean);

    @Query("SELECT * FROM t_urls")
    public List<UrlBean> getAllUrls();


}
