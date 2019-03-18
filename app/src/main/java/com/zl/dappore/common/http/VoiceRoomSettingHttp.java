package com.zl.dappore.common.http;

import com.qsmaxmin.qsbase.common.aspect.Body;
import com.qsmaxmin.qsbase.common.aspect.POST;
import com.qsmaxmin.qsbase.common.aspect.Query;
import com.zl.dappore.account.model.CheckCodeRequstBody;
import com.zl.dappore.account.model.LoginRequstBody;
import com.zl.dappore.account.model.RegisteRequstBody;
import com.zl.dappore.account.model.UserResponse;
import com.zl.dappore.common.model.BaseModel;
import com.zl.dappore.userinfo.model.UserEditRequstBody;
import com.zl.dappore.voiceroom.model.BaseVoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.LabelList;
import com.zl.dappore.voiceroom.model.TypeList;
import com.zl.dappore.voiceroom.model.VoiceRoomGreetingRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLabelRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomLogoRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomNameRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomPwdRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomRequestBody;
import com.zl.dappore.voiceroom.model.VoiceRoomTypeRequestBody;


public interface VoiceRoomSettingHttp {

    @POST("/api/room/set_chatroom_name")
    BaseModel setVoiceRoomName(@Body VoiceRoomNameRequestBody body);

    @POST("/api/room/set_chatroom_pic")
    BaseModel setVoiceRoomLogo(@Body VoiceRoomLogoRequestBody body);

    @POST("/api/room/set_chatroom_type")
    BaseModel setVoiceRoomType(@Body VoiceRoomTypeRequestBody body);

    @POST("/api/room/set_chatroom_password")
    BaseModel setVoiceRoomPwd(@Body VoiceRoomPwdRequestBody body);

    @POST("/api/room/setchatroom_notice")
    BaseModel setVoiceRoomGreeting(@Body VoiceRoomGreetingRequestBody body);

    @POST("/api/room/set_chatroom_tag")
    BaseModel setVoiceRoomLabel(@Body VoiceRoomLabelRequestBody body);

    @POST("/api/room/get_room_type")
    TypeList getVoiceRoomTypeList(@Body BaseVoiceRoomRequestBody body);

    @POST("/api/room/get_tag_list")
    LabelList getVoiceRoomLabelList(@Body BaseVoiceRoomRequestBody body);

}
