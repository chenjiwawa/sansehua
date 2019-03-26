package com.tricolorflower.heartbeat.voiceroom.model.voiceroom;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

import java.io.Serializable;
import java.util.List;

public class VoiceRoomResponse extends BaseModel implements Serializable {

    /*房间*/
    @SerializedName("data")
    public VoiceRoom data;

}
