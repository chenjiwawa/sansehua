package com.zl.dappore.voiceroom.model.voicerole;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VoiceRole extends BaseVoiceRole implements Serializable {

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
