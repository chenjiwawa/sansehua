package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

public class BaseVoiceRole {

    public static final int VOICE_HOLDER = 1;
    public static final int VOICE_CLIENT = 2;
    public static final int VOICE_ADMIN_CLIENT = 3;
    public static final int VOICE_AUDITOR = 4;
    public static final int VOICE_ADMIN_AUDITOR = 5;

    @SerializedName("id")
    public String id;
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
    @SerializedName("name")
    public String name;
    @SerializedName("logo")
    public String logo;
    @SerializedName("isMute")
    public boolean isMute;
    @SerializedName("isJoinChannel")
    public boolean isJoinChannel;

}
