package com.tricolorflower.heartbeat.voiceroom.model.message;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

import java.io.Serializable;

public class BaseMessageExtra implements Serializable {

    @SerializedName("messageExtraInfo")
    public BaseMessageExtraInfo messageExtraInfo;

    @SerializedName("messageExtraContent")
    public BaseMessageExtraContent messageExtraContent;

}
