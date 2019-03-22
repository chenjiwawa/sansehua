package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.tricolorflower.heartbeat.account.model.LoginRequstBody;
import com.tricolorflower.heartbeat.account.model.RegisteRequstBody;
import com.tricolorflower.heartbeat.account.model.UserResponse;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.userinfo.model.UserEditRequstBody;


public interface PersonalInfoHttp {

    @GET("/api/users/")
    UserResponse requestPersonalInfo(@Path String[] id);

}
