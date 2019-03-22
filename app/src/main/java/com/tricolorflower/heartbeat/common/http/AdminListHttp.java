package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.model.BaseSearchRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.home.model.AppList;
import com.tricolorflower.heartbeat.home.model.VideoList;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface AdminListHttp {
    public static final String PARAM_APP = "App";
    public static final String PARAM_VIDEO = "Video";

    @GET("/api/room/get_admin_list")
    VoiceRoleList requestAdminList(@Body BaseRequstBody body);

    @GET("/api/room/delete_admin")
    BaseModel deleteAdmin(@Body BaseVoiceRoleRequestBody body);

    @GET("/api/room/add_admin")
    BaseModel addAdmin(@Body BaseVoiceRoleRequestBody body);

    @GET("/api/room/search_admin")
    VoiceRoleList searchAdmin(@Body BaseSearchRequestBody body);

}