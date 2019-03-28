package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.GET;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoleRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.chatroom.ChatRoomInputMessageRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.ChangeVoicePositionClientRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionClientRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMicrophoneRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionMusicPermissionRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceposition.VoicePositionRequestBody;

/**
 * @CreateBy qsmaxmin
 * @Date 2017/5/18 17:37
 * @Description
 */

public interface ChatRoomHttp {

    @GET("/api/room_message/send_message")
    BaseModel sendMessage(@Body ChatRoomInputMessageRequestBody body);

}