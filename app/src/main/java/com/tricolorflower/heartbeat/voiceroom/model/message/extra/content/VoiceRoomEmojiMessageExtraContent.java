package com.tricolorflower.heartbeat.voiceroom.model.message.extra.content;


import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

public class VoiceRoomEmojiMessageExtraContent extends VoiceRoomWithFromMessageExtraContent{

    @SerializedName("'emoji_info")
    public EmojiList.Emoji emoji;

}
