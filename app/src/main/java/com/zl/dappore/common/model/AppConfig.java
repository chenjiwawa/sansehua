package com.zl.dappore.common.model;

import com.qsmaxmin.qsbase.common.config.Property;
import com.qsmaxmin.qsbase.common.config.PropertyCallback;
import com.qsmaxmin.qsbase.common.config.QsProperties;
import com.qsmaxmin.qsbase.common.log.L;


/**
 * @CreateBy qsmaxmin
 * @Date 16/7/29
 * @Description
 */
public class AppConfig extends QsProperties {

    /**
     * 单例模式
     */
    @Override
    public String initTag() {
        return "AppConfig";
    }

    @Override
    public int initType() {
        return QsProperties.OPEN_TYPE_DATA;
    }

    private static AppConfig APP_CONFIG = new AppConfig("AppConfig");

    public static AppConfig getInstance() {
        return APP_CONFIG;
    }

    private AppConfig() {
    }

    public AppConfig(String configName) {
        super(configName);
    }

    /*以下属性仅做测试使用*/
    @Property
    public String currentNetworkHost;//当前主机地址，仅作测试使用
    @Property
    public String customNetworkHost;//自定义的主机地址，仅作测试使用
    @Property
    public boolean notCheckVerify;//是否检查验证码，仅作测试使用
    @Property
    public boolean notOfflineKey;//是否是线下key

    /*序列化数据*/
    @Property
    public String userId;                  // 用户名Id
    @Property
    public boolean isFavoriteListClick;   // 我的页面，我的收藏列表是否点击过
    @Property
    public String showTagSkin;           // 当前日期，格式：yyyyMMdd

    @Property
    public boolean isMobileDownload;          // 是否开启网络提示（true为开启）
    @Property
    public boolean splashIsAdvertOpen;     // splash是否开启广告
    @Property
    public String splashBackgroundPath;    // splash背景图
    @Property
    public String splashJumpUrl;              // splash跳转链接
    @Property
    public boolean appExists;                 // 是否开启更多应用

    @Property
    public String mediaTitle;         // 自媒体标题
    @Property
    public String mediaContent;         // 自媒体内容
    @Property
    public String ttfName;         // 自媒体用户选择的字体
    @Property
    public String mediaFontId;         // 自媒体字体Id
    @Property
    public String dirName;         // 自媒体文件夹名称
    @Property
    public int imgCount;         // 自媒体图片的数量


    /**
     * 登录成功保存用户id，成功后根据用户idUserConfig
     */
    public void updateCurrentUserId(String userId, PropertyCallback callback) {
        this.userId = userId;
        L.e(initTag(), "updateCurrentUserId=" + userId);
        if (callback != null) {
            super.commit(callback);
        } else {
            super.commit();
        }
    }

    /**
     * 设置是否可以进行流量下载
     */
    public void setCanMobileDownload(boolean isMobileDownload) {
        this.isMobileDownload = isMobileDownload;
        super.commit();
    }


    /**
     * 设置自媒体标题
     */
    public void setMediaTitle(String title) {
        this.mediaTitle = title;
        super.commit();
    }

    /**
     * 设置自媒体内容
     */
    public void setMediaContent(String content) {
        this.mediaContent = content;
        super.commit();
    }

    /**
     * 设置自媒体文件夹
     */
    public void setMediaDirName(String dirName) {
        this.dirName = dirName;
        super.commit();
    }


    /**
     * 设置自媒体图片数量
     */
    public void setMediaImgCount(int count) {
        this.imgCount = count;
        super.commit();
    }

}
