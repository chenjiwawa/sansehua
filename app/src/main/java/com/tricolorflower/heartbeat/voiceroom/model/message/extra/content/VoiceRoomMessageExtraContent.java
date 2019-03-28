package com.tricolorflower.heartbeat.voiceroom.model.message.extra.content;


import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.io.Serializable;

public class VoiceRoomMessageExtraContent extends BaseMessageExtraContent implements Serializable {

    @SerializedName("room_info")
    public VoiceRoom voiceRoom;

}
