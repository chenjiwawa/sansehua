package com.tricolorflower.heartbeat.voiceroom.model.message.extra;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomVehicleMessageExtraContent;

import java.io.Serializable;

public class VoiceRoomVehicleMessageExtra extends VoiceRoomWithFromMessageExtra implements Serializable {

    @SerializedName("content")
    public VoiceRoomVehicleMessageExtraContent messageExtraContent;

    protected VoiceRoomVehicleMessageExtra parse(String messageExtra) {
        Gson gson = new Gson();
        return gson.fromJson(messageExtra, VoiceRoomVehicleMessageExtra.class);
    }

}
