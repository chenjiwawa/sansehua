package com.tricolorflower.heartbeat.voiceroom.model.message.extra.content;


import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

import java.io.Serializable;

public class VoiceRoomWithFromMessageExtraContent extends VoiceRoomMessageExtraContent implements Serializable {

    @SerializedName("from_user")
    public VoiceRole from;

}
