package com.xiaoniu.permissionutil.manager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;

import com.xiaoniu.permissionutil.listener.PermissionListener;
import com.xiaoniu.permissionutil.request.PermissionRequest;
import com.xiaoniu.permissionutil.request.PermissionRequestFragment;
import com.xiaoniu.permissionutil.util.Util;

/**
 * @author admin
 */
public class PermissionManager {

    private static final String FRAGMENT_TAG = "com.xiaoniu.permissionutil.manager";
    /**
     * 检查权限
     * @param context
     * @param permission 例如 Manifest.permission.READ_PHONE_STATE
     * @return
     */
    public static boolean checkPermission(Context context, String permission){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Util.getTargetVersion(context) >= Build.VERSION_CODES.M){
            return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }else{
            return context.getPackageManager().checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        }
    }

    /**
     * 申请权限
     * @param context
     * @param permissionRequest
     *
     */
    public static void requestPermission(@NonNull Activity context, @NonNull PermissionRequest permissionRequest){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Util.getTargetVersion(context) < Build.VERSION_CODES.M ){
            Util.showSettingPermissionDialog(context, permissionRequest.getPermissionRationaleMsg());
            permissionRequest.destory();
        }else {
            FragmentManager fm = context.getFragmentManager();
            PermissionRequestFragment currentFragment = (PermissionRequestFragment) fm.findFragmentByTag(FRAGMENT_TAG);
            if (currentFragment == null){
                currentFragment = PermissionRequestFragment.newInstance();
                fm.beginTransaction().add(currentFragment, FRAGMENT_TAG).commitAllowingStateLoss();
                fm.executePendingTransactions();//立刻执行
            }
            currentFragment.requestPermission(permissionRequest);
        }
    }
}
