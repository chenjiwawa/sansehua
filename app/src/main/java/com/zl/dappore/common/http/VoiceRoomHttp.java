package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.voiceroom.model.voicerole.BaseVoiceRoleRequestBody;
import com.zl.dappore.voiceroom.model.voiceroom.BaseVoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.voiceroom.CreateVoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.voiceroom.VoiceRoomResponse;


public interface VoiceRoomHttp {

    @POST("/api/room/create_chatroom")
    VoiceRoomResponse createVoiceRoom(@Body CreateVoiceRoomRequestBody body);

    @POST("/api/Room/add_chatroom")
    VoiceRoomResponse joinVoiceRoom(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/Room/get_chatroom_info")
    BaseModel getVoiceRoomInfo(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/Room/get_user_info")
    BaseModel getVoiceRoleInfo(@Body BaseVoiceRoleRequestBody body);

}
