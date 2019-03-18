package com.zl.dappore.userinfo.model;

import com.qsmaxmin.qsbase.common.model.QsModel;

/**
 * @CreateBy qsmaxmin
 * @Date 16/8/18  下午3:14
 * @Description 请求体基类, 请求体必须继承该类
 */
public class UserEditRequstBody extends QsModel {
    public String token;
    public String pic;
    public String nickname;
    public String birthday;
    public String sex;

    public UserEditRequstBody(String token, String pic, String nickname, String birthday, String sex) {
        this.token = token;
        this.pic = pic;
        this.nickname = nickname;
        this.birthday = birthday;
        this.sex = sex;
    }
}
