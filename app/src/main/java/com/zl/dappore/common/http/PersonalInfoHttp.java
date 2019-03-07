package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Path;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.account.model.LoginRequstBody;
import com.zl.dappore.account.model.RegisteRequstBody;
import com.zl.dappore.account.model.UserResponse;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.userinfo.model.UserEditRequstBody;


public interface PersonalInfoHttp {

    @GET("/api/users/")
    UserResponse requestPersonalInfo(@Path String[] id);

}
