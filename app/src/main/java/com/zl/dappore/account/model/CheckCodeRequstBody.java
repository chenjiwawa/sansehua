package com.zl.dappore.account.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class CheckCodeRequstBody extends QsModel {
    public String phone;

    public CheckCodeRequstBody(String phone) {
        this.phone = phone;
    }
}
