package com.tricolorflower.heartbeat.voiceroom.model.message;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

public class BaseMessageExtraInfo {

    public static final int CHATROOM_ROOM_SERVER_2_CLIENT = 1;
    public static final int CHATROOM_ROOM_CLIENT_2_CLIENT = 2;

    @SerializedName("type")
    public int type;
    @SerializedName("content")
    public String content;
    @SerializedName("extra")
    public String extra;

}
