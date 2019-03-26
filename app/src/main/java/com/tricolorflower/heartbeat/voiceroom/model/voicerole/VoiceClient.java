package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.google.gson.annotations.SerializedName;

public class VoiceClient extends VoiceRole {

    public boolean isEmojiLooper;
    public EmojiList.Emoji emoji;

    @Override
    public String toString() {
        return "VoiceClient{" +
                "isEmojiLooper=" + isEmojiLooper +
                ", emoji=" + emoji +
                ", position=" + position +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", agoraChannelName='" + agoraChannelName + '\'' +
                ", agoraUid='" + agoraUid + '\'' +
                ", rongChatRoomId='" + rongChatRoomId + '\'' +
                ", rongChatRoomType=" + rongChatRoomType +
                ", voiceRole=" + voiceRole +
                ", permission=" + permission +
                ", musicEnable=" + musicEnable +
                ", voiceMute=" + voiceMute +
                ", voiceEnable=" + voiceEnable +
                '}';
    }
}
