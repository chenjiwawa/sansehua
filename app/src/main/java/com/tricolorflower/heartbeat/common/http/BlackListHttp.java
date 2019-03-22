package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.model.BaseSearchRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface BlackListHttp {
    public static final String PARAM_APP = "App";
    public static final String PARAM_VIDEO = "Video";

    @GET("/api/Blacklist/list")
    VoiceRoleList requestBlackList(@Body BaseRequstBody body);

    @GET("/api/room/delete_blacklist")
    BaseModel deleteBlack(@Body BaseVoiceRoleRequestBody body);

    @GET("/api/room/add_blacklist")
    BaseModel addBlack(@Body BaseVoiceRoleRequestBody body);

}