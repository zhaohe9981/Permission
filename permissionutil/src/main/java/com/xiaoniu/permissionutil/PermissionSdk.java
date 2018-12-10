package com.xiaoniu.permissionutil;

import android.app.Activity;
import android.text.TextUtils;

import com.xiaoniu.permissionutil.listener.PermissionListener;
import com.xiaoniu.permissionutil.manager.PermissionManager;
import com.xiaoniu.permissionutil.request.PermissionRequest;

/**
 * @author xiaoniu
 * @date 2018/11/14.
 *
 */

public class PermissionSdk {
    private static final String TAG = "PermissionSdk";
    /**
     * 检查权限
     * @param activity
     * @param permission
     */
    public static boolean checkPermission(Activity activity, String permission){
        if (activity == null){
            throw new NullPointerException("checkPermission activity == null");
        }
        if (TextUtils.isEmpty(permission)){
            throw new NullPointerException("checkPermission permission empty");
        }
       return  PermissionManager.checkPermission(activity, permission);
    }

    /**
     * 申请权限
     * @param activity
     * @param permission
     * @param listener
     */
    public static void requestPermission(Activity activity, String permission, String permissionRationaleMsg, PermissionListener listener){
        if (activity == null){
            throw new NullPointerException("requestPermission activity == null");
        }
        if (TextUtils.isEmpty(permission)){
            throw new NullPointerException("requestPermission permission empty");
        }
        //1.0 先检查是否有权限
        //1.1 有权限直接回调
        //1.2 没有权限就去申请权限
        if (PermissionManager.checkPermission(activity, permission)){
            if (listener != null){
                listener.permissionSuccess();
            }
        }else{
            PermissionManager.requestPermission(activity, new PermissionRequest(permission, permissionRationaleMsg, listener));
        }
    }
}
