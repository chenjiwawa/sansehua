package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseVoiceRole implements Serializable {

    public static final int VOICE_AUDITOR = 0;
    public static final int VOICE_ADMIN_AUDITOR = 1;
    public static final int VOICE_CLIENT = 2;
    public static final int VOICE_ADMIN_CLIENT = 3;
    public static final int VOICE_HOLDER = 5;

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("logo")
    public String logo;

    @SerializedName("channelName")
    public String channelName;
    @SerializedName("uid")
    public String uid;

    @SerializedName("chatRoomId")
    public String chatRoomId;
    @SerializedName("type")
    public int type;

    @SerializedName("voiceRole")
    public int voiceRole;
    @SerializedName("musicEnable")
    public boolean musicEnable;
    @SerializedName("voiceMute")
    public boolean voiceMute;
    @SerializedName("voiceEnable")
    public boolean voiceEnable;

}
