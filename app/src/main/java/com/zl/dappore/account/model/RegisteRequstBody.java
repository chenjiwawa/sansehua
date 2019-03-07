package com.zl.dappore.account.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class RegisteRequstBody extends QsModel {
    public String account;
    public String token;
    public String password;

    public RegisteRequstBody(String account, String token, String password) {
        this.account = account;
        this.token = token;
        this.password = password;
    }
}
