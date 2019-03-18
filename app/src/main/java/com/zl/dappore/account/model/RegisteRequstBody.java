package com.zl.dappore.account.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class RegisteRequstBody extends QsModel {
    public String phone;
    public String password;
    public String sms;
    public String reg_ip;

    public RegisteRequstBody(String phone, String password, String sms, String reg_ip) {
        this.phone = phone;
        this.password = password;
        this.sms = sms;
        this.reg_ip = reg_ip;
    }
}
