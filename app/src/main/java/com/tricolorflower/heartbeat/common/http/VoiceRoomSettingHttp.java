package com.tricolorflower.heartbeat.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.BaseVoiceRoomSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.LabelList;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.TypeList;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomGreetingSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomLabelSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomLogoSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomNameSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomPwdSettingRequestBody;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroomsetting.VoiceRoomTypeSettingRequestBody;


public interface VoiceRoomSettingHttp {

    @POST("/api/room/set_chatroom_name")
    BaseModel setVoiceRoomName(@Body VoiceRoomNameSettingRequestBody body);

    @POST("/api/room/set_chatroom_pic")
    BaseModel setVoiceRoomLogo(@Body VoiceRoomLogoSettingRequestBody body);

    @POST("/api/room/set_chatroom_type")
    BaseModel setVoiceRoomType(@Body VoiceRoomTypeSettingRequestBody body);

    @POST("/api/room/set_chatroom_password")
    BaseModel setVoiceRoomPwd(@Body VoiceRoomPwdSettingRequestBody body);

    @POST("/api/room/setchatroom_notice")
    BaseModel setVoiceRoomGreeting(@Body VoiceRoomGreetingSettingRequestBody body);

    @POST("/api/room/set_chatroom_tag")
    BaseModel setVoiceRoomLabel(@Body VoiceRoomLabelSettingRequestBody body);

    @POST("/api/room/get_room_type")
    TypeList getVoiceRoomTypeList(@Body BaseVoiceRoomSettingRequestBody body);

    @POST("/api/room/get_tag_list")
    LabelList getVoiceRoomLabelList(@Body BaseVoiceRoomSettingRequestBody body);

}
