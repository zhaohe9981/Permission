package com.xiaoniu.permissionapp;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.xiaoniu.permissionutil.PermissionSdk;
import com.xiaoniu.permissionutil.listener.PermissionListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkCameraPermission(View view){
        if(PermissionSdk.checkPermission(MainActivity.this, Manifest.permission.CAMERA)){
            Toast.makeText(this,"已经有相机权限了", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"没有相机权限", Toast.LENGTH_SHORT).show();
        }
    }


    public void getCameraPermission(View view){
        String permissionRationaleMsg = "需要申请相机权限";
        PermissionSdk.requestPermission(MainActivity.this, Manifest.permission.CAMERA, permissionRationaleMsg, new PermissionListener() {
            @Override
            public void permissionSuccess() {
                Toast.makeText(MainActivity.this,"相机权限成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkCallPermission(View view){
        if(PermissionSdk.checkPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE)){
            Toast.makeText(this,"已经有读取手机状态权限了", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"没有读取手机状态权限", Toast.LENGTH_SHORT).show();
        }
    }

    public void getCallPermission(View view){

        String permissionRationaleMsg = "需要申请读取手机状态权限";
        PermissionSdk.requestPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE, permissionRationaleMsg, new PermissionListener() {
            @Override
            public void permissionSuccess() {
                Toast.makeText(MainActivity.this,"读取手机状态权限成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
