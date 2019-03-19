package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.voiceroom.model.BaseVoiceRoleRequestBody;
import com.zl.dappore.voiceroom.model.BaseVoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.BaseVoiceRoomSettingRequestBody;
import com.zl.dappore.voiceroom.model.CreateVoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.LabelList;
import com.zl.dappore.voiceroom.model.TypeList;
import com.zl.dappore.voiceroom.model.VoiceRoom;
import com.zl.dappore.voiceroom.model.VoiceRoomGreetingSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLabelSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLogoSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomNameSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomPwdSettingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomResponce;
import com.zl.dappore.voiceroom.model.VoiceRoomTypeSettingRequestBody;


public interface VoiceRoomHttp {

    @POST("/api/room/create_chatroom")
    VoiceRoomResponce createVoiceRoom(@Body CreateVoiceRoomRequestBody body);

    @POST("/api/Room/add_chatroom")
    VoiceRoomResponce joinVoiceRoom(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/Room/get_chatroom_info")
    BaseModel getVoiceRoomInfo(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/Room/get_user_info")
    BaseModel getVoiceRoleInfo(@Body BaseVoiceRoleRequestBody body);

}
