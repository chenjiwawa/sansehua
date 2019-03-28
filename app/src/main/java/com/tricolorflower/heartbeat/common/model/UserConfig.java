package com.tricolorflower.heartbeat.common.model;

import android.text.TextUtils;

import com.tricolorflower.heartbeat.common.event.UserInfoEvent;
import com.tricolorflower.heartbeat.account.model.User;
import com.qsmaxmin.qsbase.common.config.Property;
import com.qsmaxmin.qsbase.common.config.PropertyCallback;
import com.qsmaxmin.qsbase.common.config.QsProperties;
import com.qsmaxmin.qsbase.common.utils.QsHelper;


/**
 * @CreateBy qsmaxmin
 * @Date 16/7/29
 * @Description
 */
public class UserConfig extends QsProperties {

    public static final int USERID_EMPTY = 0;

    /**
     * 单例模式
     */
    private volatile static UserConfig USER_CONFIG;

    private UserConfig() {
    }

    private UserConfig(String configName) {
        super(configName);
    }

    public static UserConfig getInstance() {
        if (USER_CONFIG == null) {
            synchronized (UserConfig.class) {
                if (USER_CONFIG == null) {//&& !TextViewUtils.isEmpty(AppConfig.getInstance().Id)
                    USER_CONFIG = new UserConfig("UserConfig" + AppConfig.getInstance().userId);
                }
            }
        }
        return USER_CONFIG;
    }

    @Override
    public String initTag() {
        return "UserConfig";
    }

    @Override
    public int initType() {
        return QsProperties.OPEN_TYPE_DATA;
    }

    @Property
    private int id;                // 用户Id
    @Property
    private String avatarUrl;              // 用户头像
    @Property
    private String name;              // 用户昵称
    @Property
    private String mobile;              // 用户手机号
    @Property
    private String email;
    @Property
    private String authToken;

    /**
     * 更新用户信息
     */
    public void updateUserInfo(User info) {
        if (info == null) return;
        if (info.id != USERID_EMPTY) this.id = info.id;
        if (!TextUtils.isEmpty(info.name)) this.name = info.name;
        if (!TextUtils.isEmpty(info.avatarUrl)) this.avatarUrl = info.avatarUrl;
        if (!TextUtils.isEmpty(info.mobile)) this.mobile = info.mobile;
        if (!TextUtils.isEmpty(info.email)) this.email = info.email;
        if (!TextUtils.isEmpty(info.authToken)) this.authToken = info.authToken;
        super.commit();
        QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
    }


    public void setId(int id) {
        if (id != USERID_EMPTY) {
            this.id = id;
            super.commit();
            QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!TextUtils.isEmpty(email)) {
            this.email = email;
            super.commit();
            QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
        }
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            this.authToken = authToken;
            super.commit();
            QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
        }
    }

    public void setAuthTokenEmpty(String authToken) {
        {
            this.authToken = authToken;
            super.commit();
            QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
        }
    }

    public void setAvatarUrl(String avatarUrl) {
        if (!TextUtils.isEmpty(avatarUrl)) {
            this.avatarUrl = avatarUrl;
            super.commit();
            QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
        }
    }

    public void setName(String name) {
        if (!TextUtils.isEmpty(name)) {
            this.name = name;
            super.commit();
            QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
        }
    }

    public void setMobile(String mobile) {
        if (!TextUtils.isEmpty(mobile)) {
            this.mobile = mobile;
            super.commit();
            QsHelper.getInstance().eventPost(new UserInfoEvent.OnUserInfoDataChanged());
        }
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public int getId() {
        return id;
    }

    public void logout(final PropertyCallback callback) {
        setAuthTokenEmpty("");
        id = USERID_EMPTY;
        USER_CONFIG = null;//切记置空
        commit(new PropertyCallback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }
        });
    }

    public boolean isLogin() {
        return this.id != USERID_EMPTY;
    }
}
