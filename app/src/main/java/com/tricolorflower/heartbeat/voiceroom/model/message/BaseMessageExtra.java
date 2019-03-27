package com.tricolorflower.heartbeat.voiceroom.model.message;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.io.Serializable;

public class BaseMessageExtra implements Serializable {

    @SerializedName("type")
    public String messageExtraType;

    @SerializedName("is_display")
    public String isDisplay;

    @SerializedName("content")
    public BaseMessageExtraContent messageExtraContent;

    @SerializedName("extra")
    public String messageExtraExtra;

}