package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VoiceRole extends BaseVoiceRole implements Serializable {

    public int sex;
    public String birthday;
    public int vip;
    public int wealth_level;//TODO
    public int charm_level;
    public int age;
    public String constellation;
    public boolean isSelect;

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
