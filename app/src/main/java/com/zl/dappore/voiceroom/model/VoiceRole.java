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
                ", channelName='" + channelName + '\'' +
                ", uid='" + uid + '\'' +
                ", chatRoomId='" + chatRoomId + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", isMute=" + isMute +
                ", isJoinChannel=" + isJoinChannel +
                '}';
    }
}
