package com.tricolorflower.heartbeat.voiceroom.model.message.extra;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.BaseMessageExtra;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomEmojiMessageExtraContent;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomGiftMessageExtraContent;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.VoiceRoomMessageExtraContent;

import java.io.Serializable;

public class VoiceRoomEmojiMessageExtra extends BaseMessageExtra implements Serializable {

    @SerializedName("content")
    public VoiceRoomEmojiMessageExtraContent messageExtraContent;


    protected VoiceRoomEmojiMessageExtra parse(String messageExtra) {
        Gson gson = new Gson();
        return gson.fromJson(messageExtra, VoiceRoomEmojiMessageExtra.class);
    }
}
