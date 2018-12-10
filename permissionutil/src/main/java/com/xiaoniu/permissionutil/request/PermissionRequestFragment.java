package com.xiaoniu.permissionutil.request;

import android.app.Activity;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.xiaoniu.permissionutil.util.Util;

/**
 *申请权限的fragment
 */
public class PermissionRequestFragment extends Fragment {

    private static final String TAG = "PermissionRequestFragme";

    private final static int REQUEST_PERMISSION = 100;

    private PermissionRequest permissionRequest;
    private boolean onAttachRequestPer = false;

    public PermissionRequestFragment() {

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (onAttachRequestPer){
            requestPermission();
            onAttachRequestPer = false;
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     */
    public static PermissionRequestFragment newInstance() {
        PermissionRequestFragment fragment = new PermissionRequestFragment();
        return fragment;
    }

    /**
     * 请求权限
     * @param permissionRequest
     */
    public void requestPermission(@NonNull PermissionRequest permissionRequest){
        Log.d(TAG, "requestPermission");
        this.permissionRequest = permissionRequest;
        if (getActivity() == null){
            onAttachRequestPer = true;
            return;
        }else {
            onAttachRequestPer = false;
            requestPermission();
        }
    }

    private void requestPermission(){
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getActivity(), permissionRequest.getPermission()) == PackageManager.PERMISSION_GRANTED) {
            if (permissionRequest.getListener() != null){
                permissionRequest.getListener().permissionSuccess();
                permissionRequest.destory();
            }
        } else if (shouldShowRequestPermissionRationale(permissionRequest.getPermission())) {
            Util.showSettingPermissionDialog(getActivity(), permissionRequest.getPermissionRationaleMsg());
            permissionRequest.destory();
        } else {
            requestPermissions(new String[]{permissionRequest.getPermission()}, REQUEST_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION && grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           if (permissionRequest.getListener() != null){
                permissionRequest.getListener().permissionSuccess();
           }
        } else {
          Util.showSettingPermissionDialog(getActivity(), permissionRequest.getPermissionRationaleMsg());
        }
        permissionRequest.destory();
    }
}
