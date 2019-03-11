package com.zl.dappore.common.utils.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.qsmaxmin.qsbase.common.log.L;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @创建人 QS
 * @创建时间 16/6/28  上午9:21
 * @类描述 系统权限管理工具
 */
public class PermissionTask {
    public final static String TAG = PermissionTask.class.getSimpleName();

    private static PermissionTask util;
    private        int            requestCode;
    private HashMap<String, PermissionUtil> maps = new HashMap<>();

    private PermissionTask() {
    }

    public static PermissionTask getInstance() {
        if (util == null) {
            util = new PermissionTask();
        }
        return util;
    }

    void startRequestPermission(PermissionUtil builder) {
        if (builder == null) return;
        if (maps.containsValue(builder)) {
            L.e(TAG,"current permission is requesting, please don't request again....");
            return;
        }
        if (builder.getActivity() == null) {
            L.e(TAG,"activity can not be null, please setActivity()");
            return;
        }
        if (builder.getWantPermissionArr().size() == 0) {
            L.e(TAG,"you has not addWantPermission(String)");
            return;
        }
        ArrayList<String> unGrantedPermission = getUnGrantedPermissionArr(builder.getWantPermissionArr());
        if (unGrantedPermission.size() > 0) {
            if (builder.getActivity() != null) {
                requestCode++;
                L.i(TAG,"start request permission  requestCode=" + requestCode + "   wantPermission=" + unGrantedPermission.toString());
                builder.setRequestCode(requestCode);
                maps.put(String.valueOf(requestCode), builder);
                ActivityCompat.requestPermissions(builder.getActivity(), unGrantedPermission.toArray(new String[unGrantedPermission.size()]), requestCode);
            }
        } else {
            L.i(TAG,"all permission is granted....");
            if (builder.getListener() != null) {
                builder.getListener().onPermissionCallback(-1, true);
            }
        }
    }

    private ArrayList<String> getUnGrantedPermissionArr(List<String> list) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String permission : list) {
            if (ContextCompat.checkSelfPermission(QsHelper.getInstance().getApplication(), permission) != PackageManager.PERMISSION_GRANTED) {//用户未授权
                arrayList.add(permission);
            }
        }
        return arrayList;
    }


    /*------------------------------------- 以下是申请权限回调的数据解析 ---------------------------------------*/
    public void parsePermissionResultData(int requestCode, String[] permissions, int[] grantResults, Activity activity) {
        PermissionUtil builder = maps.remove(String.valueOf(requestCode));
        if (builder == null) return;
        boolean grantedAll = true;
        ArrayList<String> unGrantedArr = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {//用户不同意，向用户展示该权限作用
                grantedAll = false;
                if (i < permissions.length && i >= 0) {
                    L.i(TAG,"user un granted permission:" + permissions[i]);
                    unGrantedArr.add(permissions[i]);
                }
            }
        }
        if (grantedAll) {
            L.i(TAG,"user granted all permission....");
            if (builder.getListener() != null) {
                builder.getListener().onPermissionCallback(builder.getRequestCode(), true);
            }

        } else {
            if (builder.getListener() != null) {
                builder.getListener().onPermissionCallback(builder.getRequestCode(), false);
            }
            if (builder.isShowCustomDialog()) {
                boolean shouldShowDialog = false;
                ArrayList<String> shouldShowDialogArr = new ArrayList<>();
                for (String unGrantedStr : unGrantedArr) {
                    if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, unGrantedStr)) {
                        shouldShowDialog = true;
                        shouldShowDialogArr.add(unGrantedStr);
                    }
                }
                if (shouldShowDialog) {
                    showPermissionTipsDialog(shouldShowDialogArr);
                }
            }

        }
    }

    /**
     * 当系统提醒请求权限的对话框勾选不再提醒时，弹出的自定义对话框
     */
    private void showPermissionTipsDialog(ArrayList<String> showDialogPermission) {
        if (showDialogPermission == null || showDialogPermission.size() < 1 || TextUtils.isEmpty(getPermissionDialogMessage(showDialogPermission))) {
            return;
        }
        L.i(TAG,"************勾选了不在提醒并弹出对话框 " + showDialogPermission.toString());
//        RoomOperationDialogFragment.createBuilder()//
//                .setTitle(QsHelper.getInstance().getString(R.string.notice))//
//                .setMessage(getPermissionDialogMessage(showDialogPermission))//
//                .setNegativeButtonText(QsHelper.getInstance().getString(R.string.cancel))//
//                .setPositiveButtonText(QsHelper.getInstance().getString(R.string.go_to_set))//
//                .setRequestCode(QsConstants.QS_DIALOG_PERMISSION_CODE)//
//                .showAllowingStateLoss();

    }

    private String getPermissionDialogMessage(ArrayList<String> permission) {
        StringBuilder stringbuilder = new StringBuilder();
        for (String str : permission) {
            switch (str) {
                case Manifest.permission.ACCESS_COARSE_LOCATION:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_location_permission)).append("  ");
                    break;
                case Manifest.permission.READ_EXTERNAL_STORAGE:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_read_external_storage_permission)).append("  ");
                    break;
                case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_write_external_storage_permission)).append("  ");
                    break;
                case Manifest.permission.READ_CONTACTS:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_constants_permission)).append("  ");
                    break;
                case Manifest.permission.CALL_PHONE:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_call_permission)).append("  ");
                    break;
                case Manifest.permission.CAMERA:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_camera_permission)).append("  ");
                    break;
                case Manifest.permission.RECORD_AUDIO:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_record_audio_permission)).append("  ");
                    break;
                case Manifest.permission.READ_PHONE_STATE:
                    stringbuilder.append(QsHelper.getInstance().getString(R.string.request_read_phone_state_permission)).append("  ");
                    break;
            }
        }
        return stringbuilder.length() < 1 ? null : stringbuilder.append(QsHelper.getInstance().getString(R.string.request_permission_end)).toString();
    }
}
