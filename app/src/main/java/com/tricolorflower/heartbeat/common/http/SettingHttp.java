package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.setting.model.CheckUpdateResponse;

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
