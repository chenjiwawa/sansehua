package com.tricolorflower.heartbeat.common.utils.permission;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/3/7 12:36
 * @Description
 */

public class PermissionUtil {

    private List<String> wantPermissionArr   = new ArrayList<>();
    private boolean      mIsShowCustomDialog = true;

    private int                mRequestCode;
    private PermissionListener mListener;
    private Activity mActivity;

    public static PermissionUtil getInstance() {
        return new PermissionUtil();
    }

    public List<String> getWantPermissionArr() {
        return wantPermissionArr;
    }

    public PermissionUtil addWantPermission(String permission) {
        if (!wantPermissionArr.contains(permission)) wantPermissionArr.add(permission);
        return this;
    }

    public boolean isShowCustomDialog() {
        return mIsShowCustomDialog;
    }

    public PermissionUtil setShowCustomDialog(boolean isShowCustomDialog) {
        this.mIsShowCustomDialog = isShowCustomDialog;
        return this;
    }

    public PermissionListener getListener() {
        return mListener;
    }

    public PermissionUtil setListener(PermissionListener listener) {
        this.mListener = listener;
        return this;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public PermissionUtil setActivity(Activity activity) {
        this.mActivity = activity;
        return this;
    }

    /**
     * 自增量
     */
    public int getRequestCode() {
        return mRequestCode;
    }

    void setRequestCode(int mRequestCode) {
        this.mRequestCode = mRequestCode;
    }

    public void startRequest() {
        PermissionTask.getInstance().startRequestPermission(this);
    }

    /**
     * 权限回调接口
     */
    public interface PermissionListener {
        /**
         * 申请权限的回调
         *
         * @param isGrantedAll 是否全部同意
         */
        void onPermissionCallback(int requestCode, boolean isGrantedAll);
    }
}
