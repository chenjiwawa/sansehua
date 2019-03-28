package com.tricolorflower.heartbeat.voiceroom.model.message.extra;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.BaseMessageExtra;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.BaseMessageExtraContent;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomMessageExtraContent;

import java.io.Serializable;

public class VoiceRoomMessageExtra extends BaseMessageExtra implements Serializable {

    @SerializedName("content")
    public VoiceRoomMessageExtraContent messageExtraContent;

    protected VoiceRoomMessageExtra parse(String messageExtra) {
        Gson gson = new Gson();
        return gson.fromJson(messageExtra, VoiceRoomMessageExtra.class);
    }

}
