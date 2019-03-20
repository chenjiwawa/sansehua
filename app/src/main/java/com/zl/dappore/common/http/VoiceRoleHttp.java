package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.voiceroom.model.voicerole.BaseVoiceRoleRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface VoiceRoleHttp {

    @GET("/api/Room/get_user_info")
    VoiceRoleList.VoiceRole requestVoiceRoleInfo(@Body BaseVoiceRoleRequestBody body);

}