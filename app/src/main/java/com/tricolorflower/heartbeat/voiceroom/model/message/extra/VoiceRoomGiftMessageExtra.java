package com.tricolorflower.heartbeat.voiceroom.model.message.extra;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomGiftMessageExtraContent;

import java.io.Serializable;

public class VoiceRoomGiftMessageExtra extends VoiceRoomWithFromMessageExtra implements Serializable {

    @SerializedName("content")
    public VoiceRoomGiftMessageExtraContent messageExtraContent;

    protected VoiceRoomGiftMessageExtra parse(String messageExtra) {
        Gson gson = new Gson();
        return gson.fromJson(messageExtra, VoiceRoomGiftMessageExtra.class);
    }

}
