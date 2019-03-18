package com.zl.dappore.common.utils;

/**
 * @CreateBy qsmaxmin
 * @Date 16/7/29
 * @Description
 */
public class UrlUtils {
    /**
     * 线上主机
     */
    public static final String URL_ONLINE = "http://4bp7b.cn/index.php";

    /**
     * 测试
     */
    public static final String URL_OFFLINE_WW = "http://192.168.248.191:9550/";

    /**
     * 获取服务端主机地址
     */
    public static String getCurrentUrl() {
        return URL_ONLINE;
    }
}
