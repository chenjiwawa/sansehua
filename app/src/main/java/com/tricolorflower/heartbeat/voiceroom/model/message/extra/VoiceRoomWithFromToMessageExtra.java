package com.tricolorflower.heartbeat.voiceroom.model.message.extra;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomWithFromToMessageExtraContent;

import java.io.Serializable;

public class VoiceRoomWithFromToMessageExtra extends VoiceRoomWithFromMessageExtra implements Serializable {

    @SerializedName("content")
    public VoiceRoomWithFromToMessageExtraContent messageExtraContent;

    protected VoiceRoomWithFromToMessageExtra parse(String messageExtra) {
        Gson gson = new Gson();
        return gson.fromJson(messageExtra, VoiceRoomWithFromToMessageExtra.class);
    }

}
