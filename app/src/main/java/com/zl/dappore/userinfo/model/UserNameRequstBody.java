package com.zl.dappore.userinfo.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class UserNameRequstBody extends QsModel {
    public String name;

    public UserNameRequstBody(String name) {
        this.name = name;
    }
}
