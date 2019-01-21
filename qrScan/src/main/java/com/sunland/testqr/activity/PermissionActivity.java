package com.sunland.testqr.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PermissionActivity extends AppCompatActivity {
    private final String TAG=this.getClass().getSimpleName();
    private String[] permission_declared={Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private List<String> permissions_apply;
    private final int REQUEST_CODE=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissions_apply=new ArrayList<>();
        for(String permission:permission_declared){
            if(ContextCompat.checkSelfPermission(this,permission)!= PackageManager.PERMISSION_GRANTED){
                permissions_apply.add(permission);
            }
        }
        if(permissions_apply!=null&&!permissions_apply.isEmpty()){
            String[] unAuthorized=permissions_apply.toArray(new String[]{});
            ActivityCompat.requestPermissions(this,unAuthorized,REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_CODE){
            for(int i=0;i<permissions.length;i++){
                if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                    boolean shouldRequest=ActivityCompat.shouldShowRequestPermissionRationale(this,permissions[i]);
                    if(!shouldRequest){
                        //to sth then...
                    }
                }
            }
        }
    }
}
