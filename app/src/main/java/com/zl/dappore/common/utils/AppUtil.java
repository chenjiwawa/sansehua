package com.zl.dappore.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.qsmaxmin.qsbase.common.utils.QsHelper;


/**
 * app util
 * @Author zhuanggy
 * @Date:2014-1-7
 * @Copyright (c) 2014, 方正电子 All Rights Reserved.
 */
public class AppUtil {
    /**
     * @brief：getAPKVersion 获取软件apk的versionName
     * @author： Hao LI
     * @since： 1.0.0.0
     * @version： 1.0.0.0
     * @param： context 上下文对象
     * @return： 返回软件apk的versionName
     */
    public static String getAPKVersion(Context context) {
        String APKVersion = "";
        PackageManager pm = context.getPackageManager();
        String packageName = context.getPackageName();
        // 得到系统安装的所有程序包的PackageInfo对象
        PackageInfo info = null;
        try {
            info = pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            APKVersion = info.versionName + ""; // 得到版 本信息
        }
        return APKVersion;
    }


    /**
     * 判断某应用是否安装
     * @param @param   packageName
     * @param @return 
     * @author zhuanggy
     * @date 2014-12-24
     */
    public static boolean checkInstall(String packageName) {
        try {
            PackageInfo packageInfo = QsHelper.getInstance().getApplication().getPackageManager().getPackageInfo(packageName, 0);
            if (packageInfo == null) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
