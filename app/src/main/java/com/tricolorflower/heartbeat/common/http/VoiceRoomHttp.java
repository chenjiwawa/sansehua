package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.CreateVoiceRoomRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.EnterVoiceRoomResponse;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoomResponse;


public interface VoiceRoomHttp {

    @POST("/api/room/create_chatroom")
    EnterVoiceRoomResponse createVoiceRoom(@Body CreateVoiceRoomRequestBody body);

    @POST("/api/Room/add_chatroom")
    EnterVoiceRoomResponse joinVoiceRoom(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/room/quit_chatroom")
    BaseModel leaveVoiceRoom(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/Room/get_chatroom_info")
    VoiceRoomResponse getVoiceRoomInfo(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/Room/get_user_info")
    BaseModel getVoiceRoleInfo(@Body BaseVoiceRoleRequestBody body);

}
