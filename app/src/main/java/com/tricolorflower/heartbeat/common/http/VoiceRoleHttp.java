package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface VoiceRoleHttp {

    @GET("/api/Room/get_user_info")
    VoiceRoleList.VoiceRole requestVoiceRoleInfo(@Body BaseVoiceRoleRequestBody body);

}