package com.xiaoniu.permissionutil.request;

import com.xiaoniu.permissionutil.listener.PermissionListener;

/**
 * @author xiaoniu
 * @date 2018/12/10.
 */

public class PermissionRequest {

    /**
     * 要申请的权限
     */
    private String permission;
    /**
     * 需要弹窗让用户确认时的提示语
     */
    private String permissionRationaleMsg;

    /**
     * 回调
     */
    private PermissionListener listener;

    public PermissionRequest() {
    }


    public PermissionRequest(String permission, String permissionRationaleMsg, PermissionListener listener) {
        this.permission = permission;
        this.permissionRationaleMsg = permissionRationaleMsg;
        this.listener = listener;
    }

    public void destory(){
        this.permission = null;
        this.permissionRationaleMsg = null;
        this.listener = null;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermissionRationaleMsg() {
        return permissionRationaleMsg;
    }

    public void setPermissionRationaleMsg(String permissionRationaleMsg) {
        this.permissionRationaleMsg = permissionRationaleMsg;
    }

    public PermissionListener getListener() {
        return listener;
    }

    public void setListener(PermissionListener listener) {
        this.listener = listener;
    }
}
