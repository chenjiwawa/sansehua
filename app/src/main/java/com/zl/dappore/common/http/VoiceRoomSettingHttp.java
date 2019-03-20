package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.voiceroom.model.voiceroomsetting.BaseVoiceRoomSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.LabelList;
import com.zl.dappore.voiceroom.model.voiceroomsetting.TypeList;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomGreetingSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomLabelSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomLogoSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomNameSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomPwdSettingRequestBody;
import com.zl.dappore.voiceroom.model.voiceroomsetting.VoiceRoomTypeSettingRequestBody;


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
