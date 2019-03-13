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
                ", channelName='" + channelName + '\'' +
                ", uid='" + uid + '\'' +
                ", chatRoomId='" + chatRoomId + '\'' +
                ", type=" + type +
                ", voiceRole=" + voiceRole +
                ", musicEnable=" + musicEnable +
                ", voiceMute=" + voiceMute +
                ", voiceEnable=" + voiceEnable +
                '}';
    }
}
