package com.zl.dappore.common.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.qsmaxmin.qsbase.common.utils.QsHelper;

import java.util.UUID;


/**
 * @CreatBy qsmaxmin
 * @Date 16/8/10  上午10:06
 * @Description 网络相关的工具类
 */
public class NetworkUtils {

    /**
     * WIFI网络
     */
    public static final String NET_TYPE_WIFI = "2";

    /**
     * 蜂窝网络
     */
    public static final String NET_TYPE_CELLULAR = "1";

    /**
     * 移动蜂窝
     */
    public static final String CELLULAR_TYPE_CM = "1";

    /**
     * 联通蜂窝
     */
    public static final String CELLULAR_TYPE_CN = "2";

    /**
     * 电信蜂窝
     */
    public static final String CELLULAR_TYPE_CT = "3";

    /**
     * 其他蜂窝
     */
    public static final String CELLULAR_TYPE_OT = "4";


    /**
     * 获取手机串号IMEI
     */
    public static String getDeviceIMEI() {
        if (ContextCompat.checkSelfPermission(QsHelper.getInstance().getApplication(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            TelephonyManager tm = (TelephonyManager) QsHelper.getInstance().getApplication().getSystemService(Context.TELEPHONY_SERVICE);
            String deviceId = tm.getDeviceId();
            return (deviceId == null || deviceId.length() == 0) ? UUID.randomUUID().toString() : tm.getDeviceId();
        }
        return "";
    }

    /**
     * 获取网络状态 蜂窝网 or WIFI 1=蜂窝网，2=WIFI
     */
    public static String getNetType() {
        if (isMobileConnected()) {
            return NET_TYPE_CELLULAR;
        } else if (isWifiConnected()) {
            return NET_TYPE_WIFI;
        }
        return "";
    }

    /**
     * 判断蜂窝网络是否连接
     */
    public static boolean isMobileConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) QsHelper.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mMobileNetworkInfo != null && mMobileNetworkInfo.isConnected();
    }

    /**
     * 判断WIFI网络是否链接
     */
    public static boolean isWifiConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) QsHelper.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWiFiNetworkInfo != null && mWiFiNetworkInfo.isConnected();
    }

    /**
     * 判断是否有网络连接
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) QsHelper.getInstance().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mConnectivityManager != null) {
            NetworkInfo[] info = mConnectivityManager.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo networkInfo : info) {
                    if (networkInfo.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 获取蜂窝网络提供商
     */
    public static String getCellularType() {
        TelephonyManager telManager = (TelephonyManager) QsHelper.getInstance().getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        String operator = telManager.getSimOperator();
        if (operator != null) {

            switch (operator) {
                case "46000":
                case "46002":
                case "46007":
                    // 中国移动
                    return CELLULAR_TYPE_CM;
                case "46001":
                    // 中国联通
                    return CELLULAR_TYPE_CN;
                case "46003":
                    // 中国电信
                    return CELLULAR_TYPE_CT;
                default:
                    return CELLULAR_TYPE_OT;
            }
        }
        return "";
    }
}
