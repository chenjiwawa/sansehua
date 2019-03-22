package com.tricolorflower.heartbeat.common.http;

import com.tricolorflower.heartbeat.account.model.CheckCodeRequstBody;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.account.model.LoginRequstBody;
import com.tricolorflower.heartbeat.account.model.RegisteRequstBody;
import com.tricolorflower.heartbeat.account.model.UserResponse;
import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.tricolorflower.heartbeat.userinfo.model.UserEditRequstBody;

import org.aspectj.lang.annotation.Pointcut;


public interface UserInfoHttp {

    @POST("/api/user/login")
    UserResponse requestLogin(@Body LoginRequstBody body);

    @POST("/api/user/register")
    UserResponse requestRegiste(@Body RegisteRequstBody body);

    @POST("/api/user/send_sms")
    BaseModel requestCheckCode(@Body CheckCodeRequstBody body);

    @POST("/api/user/edit_data")
    UserResponse requestUserEdit(@Query("account") String account, @Body UserEditRequstBody body);

}
