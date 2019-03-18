package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.home.model.AppList;
import com.zl.dappore.voicerolelist.model.VoiceRoleList;
import com.zl.dappore.voicerolelist.model.VoiceRoleRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface VoiceRoleListHttp {

    @GET("/api/Room/get_chatroom_member")
    VoiceRoleList requestVoiceRoleList(@Body VoiceRoleRequestBody body);

}