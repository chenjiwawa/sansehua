package com.zl.dappore.userinfo.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class UserEditRequstBody extends QsModel {
    public String name;
    public String avatar_url;

    public UserEditRequstBody(String name, String avatar_url) {
        this.name = name;
        this.avatar_url = avatar_url;
    }
}
