package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRoleResponse;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface VoiceRoleHttp {

    @GET("/api/Room/get_user_info")
    VoiceRoleResponse requestVoiceRoleInfo(@Body BaseVoiceRoleRequestBody body);

}