package com.zl.dappore.common.http;

import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.account.model.LoginRequstBody;
import com.zl.dappore.account.model.RegisteRequstBody;
import com.zl.dappore.account.model.UserResponse;
import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.userinfo.model.UserEditRequstBody;


public interface UserInfoHttp {

    @POST("/api/join")
    UserResponse requestLogin(@Body LoginRequstBody body);

    @POST("/api/join")
    UserResponse requestRegiste(@Body RegisteRequstBody body);

    @GET("/api/join")
    BaseModel requestCheckCode(@Query("account") String account);

    @POST("/api/me")
    UserResponse requestUserEdit(@Query("account") String account,@Body UserEditRequstBody body);

}
