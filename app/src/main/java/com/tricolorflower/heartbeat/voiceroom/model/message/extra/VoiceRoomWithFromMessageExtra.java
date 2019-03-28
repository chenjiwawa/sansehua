package com.tricolorflower.heartbeat.voiceroom.model.message.extra;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomWithFromMessageExtraContent;

import java.io.Serializable;

public class VoiceRoomWithFromMessageExtra extends VoiceRoomMessageExtra implements Serializable {

    @SerializedName("content")
    public VoiceRoomWithFromMessageExtraContent messageExtraContent;

    protected VoiceRoomWithFromMessageExtra parse(String messageExtra) {
        Gson gson = new Gson();
        return gson.fromJson(messageExtra, VoiceRoomWithFromMessageExtra.class);
    }

}
