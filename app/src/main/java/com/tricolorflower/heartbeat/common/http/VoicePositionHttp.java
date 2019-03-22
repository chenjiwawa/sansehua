package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.voicerolelist.model.VoiceRoleList;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionClientRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMicrophoneRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMusicPermissionRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface VoicePositionHttp {

    @GET("/api/room/set_lock_seat")
    BaseModel setVoicePosition(@Body VoicePositionRequestBody body);

    @GET("/api/room/set_music")
    BaseModel setVoicePositionMusicPermission(@Body VoicePositionMusicPermissionRequestBody body);

    @GET("/api/room/set_voice")
    BaseModel setVoicePositionMicrophone(@Body VoicePositionMicrophoneRequestBody body);

    @GET("/api/room/upper_seat")
    BaseModel loginVoicePosition(@Body VoicePositionClientRequestBody body);

    @GET("/api/room/out_seat")
    BaseModel logoutVoicePosition(@Body VoicePositionClientRequestBody body);

    @GET("/api/room/shot_chatroom")
    BaseModel kickOutVoicePosition(@Body BaseVoiceRoleRequestBody body);

}