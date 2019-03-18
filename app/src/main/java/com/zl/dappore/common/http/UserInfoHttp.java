package com.zl.dappore.common.http;

import com.zl.dappore.account.model.CheckCodeRequstBody;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.account.model.LoginRequstBody;
import com.zl.dappore.account.model.RegisteRequstBody;
import com.zl.dappore.account.model.UserResponse;
import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.userinfo.model.UserEditRequstBody;

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
