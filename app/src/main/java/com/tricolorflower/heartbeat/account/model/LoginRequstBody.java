package com.tricolorflower.heartbeat.account.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class LoginRequstBody extends QsModel {
    public String phone;
    public String password;

    public LoginRequstBody(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }
}
