package com.zl.dappore.account.model;

import com.zl.dappore.common.model.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:38
 * @Description
 */

public class UserResponse extends BaseModel {

    /**
     * user : {"id":"Beijing","name":"小白菜","email":"mingyuan0715@foxmail.com","email_confirmed":false,"mobile":"","mobile_confirmed":false,"password_digest":"$2a$10$sywoNCDI8AMAwd6DzNeX2eveXSMaF2ZTy.mpipeWXmMqpfkwbzkoG","last_login_at":"2018-11-20T11:52:11.270+08:00","last_login_ip":"Beijing","disabled":false,"timezone":"Beijing","locale":"Beijing","created_at":"2018-11-05T14:56:11.290+08:00","updated_at":"2018-11-22T10:23:20.159+08:00","nation":"Beijing","source":"Beijing","avatar_url":"http://assets-yigenongfu.one.work/VBj8d8oJUrCcsYKwQZ3xnQ66","oauth_users":[]}
     */

    @SerializedName("token")
    public String token;
    @SerializedName("user")
    public User user;

    @Override
    public String toString() {
        return "UserResponse{" +
                "token='" + token + '\'' +
                ", user=" + user +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
