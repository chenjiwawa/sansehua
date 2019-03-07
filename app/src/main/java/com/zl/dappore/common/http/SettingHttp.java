package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.setting.model.CheckUpdateResponse;

/**
 * 设置模块相关的接口请求 Created by zhuanggy on 2016/8/8.
 */
public interface SettingHttp {
    /**
     * 检查更新
     */
    @POST("/api/fsa/app/version/checkVersion.json")
    CheckUpdateResponse checkUpdate(@Body BaseModel body);


}
