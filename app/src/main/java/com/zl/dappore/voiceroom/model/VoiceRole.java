package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

public class VoiceRole extends BaseVoiceRole {

    @SerializedName("position")
    public int position;


    @Override
    public String toString() {
        return "VoiceRole{" +
                "position=" + position +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", agoraChannelName='" + agoraChannelName + '\'' +
                ", agoraUid='" + agoraUid + '\'' +
                ", rongChatRoomId='" + rongChatRoomId + '\'' +
                ", rongChatRoomType=" + rongChatRoomType +
                ", voiceRole=" + voiceRole +
                ", musicEnable=" + musicEnable +
                ", voiceMute=" + voiceMute +
                ", voiceEnable=" + voiceEnable +
                '}';
    }
}
