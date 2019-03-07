package com.zl.dappore.common.model;

import android.os.Build;

import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.zl.dappore.R;
import com.zl.dappore.common.utils.ApplicationUtil;
import com.zl.dappore.common.utils.CommonUtils;
import com.zl.dappore.common.utils.NetworkUtils;
import com.zl.dappore.common.utils.UrlUtils;


/**
 * @CreateBy qsmaxmin
 * @Date 15/4/10 上午10:37
 * @Description 公共常量
 */
public class AppConstants {

    /**
     * app key 线上
     */
//    private static final String APP_KEY_ONLINE  = "c10deb3d-e385-4992-a877-fa0d7cb70280";//手迹字体  *******
//    private static final String APP_KEY_ONLINE  = "0a9a9bdb-4ce8-4159-9d8b-e8193e22bd24";//手迹字体线上测试   123456l
    private static final String APP_KEY_ONLINE = "6bffd771-8bee-4ca8-9f78-a54f4e2b7e5a";//手迹字体线上测试   123456l

    /**
     * app key 线下
     */
//    private static final String APP_KEY_OFFLINE = "c10deb3d-e385-4992-a877-fa0d7cb70280";//254df809-12f8-4fb6-b3af-2abd2c538797
//    private static final String APP_KEY_OFFLINE = "0a9a9bdb-4ce8-4159-9d8b-e8193e22bd24";//254df809-12f8-4fb6-b3af-2abd2c538797
    private static final String APP_KEY_OFFLINE = "6bffd771-8bee-4ca8-9f78-a54f4e2b7e5a";//

    /**
     * AppKey
     */
    public static String getAppKey() {
        if (QsHelper.getInstance().getApplication().isLogOpen()) {
            return getOfflineKey();
        } else {
            return APP_KEY_ONLINE;
        }
    }

    private static String getOfflineKey() {
        return UrlUtils.URL_ONLINE.equals(UrlUtils.getCurrentUrl()) ? APP_KEY_ONLINE : (AppConfig.getInstance().notOfflineKey ? APP_KEY_ONLINE : APP_KEY_OFFLINE);
    }

    /**
     * SHA值
     */
    public static final String SHA = ApplicationUtil.getCertificateSHA1Fingerprint(QsHelper.getInstance().getApplication());
    /**
     * 包名
     */
    public static final String PACKAGE_NAME = QsHelper.getInstance().getApplication().getPackageName();
    /**
     * 客户端适用于系统 android
     */
    public static final String APP_OS = "android";
    /**
     * 客户端 代号
     */
    public static final String APP_CODE = "Fonts";
    /**
     * 客户端 版本号
     */
    public static final String CLIENT_VERSION = ApplicationUtil.getAppClientVersion();
    /**
     * 友盟统计渠道
     */
    public static final String UMENG_CHANNEL = CommonUtils.getMetaInfo("UMENG_CHANNEL", "Founder");
    /**
     * 设备唯一标示
     */
    public static final String IMEI = NetworkUtils.getDeviceIMEI();
    /**
     * 设备系统版本号
     */
    public static final String DEVICE_VERSION = Build.VERSION.RELEASE;
    /**
     * 手机厂商
     */
    public static final String DEVICE_FACTORY = Build.MANUFACTURER;
    /**
     * 手机型号
     */
    public static final String DEVICE_MODEL = Build.MODEL;
    /**
     * 数据库名称
     */
    public static final String DB_NAME = "FOUNDER_DB";
    /**
     * App在SD卡中的父目录
     */
    public static final String PATH_PARENT = "/" + QsHelper.getInstance().getString(R.string.path_parent);
    /**
     * 图片缓存目录path
     */
    public static final String PATH_IMG_CACHE = PATH_PARENT + QsHelper.getInstance().getString(R.string.path_image_cache);
    /**
     * 图片保存目录
     */
    public static final String PATH_IMG = PATH_PARENT + QsHelper.getInstance().getString(R.string.path_img);
    /**
     * 统一下载路径(App升级，应用下载的root工具等)
     */
    public static final String PATH_DOWNLOAD = PATH_PARENT + QsHelper.getInstance().getString(R.string.path_download);
    /**
     * 图片缓存目录大小
     */
    public static final int IMG_CACHE_SIZE = 300 * 1024 * 1024;
}
