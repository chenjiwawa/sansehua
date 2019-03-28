package com.tricolorflower.heartbeat.voiceroom.model.message.extra.content;


import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

import java.io.Serializable;

public class VoiceRoomWithFromToMessageExtraContent extends VoiceRoomWithFromMessageExtraContent implements Serializable {

    @SerializedName("to_user")
    public VoiceRole to;

}
