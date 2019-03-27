package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;

import java.io.Serializable;

public class VoiceRoleResponse extends BaseModel implements Serializable {

    @SerializedName("data")
    public VoiceRole data;

}
