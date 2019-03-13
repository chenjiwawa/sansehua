package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

public class BaseVoiceRole {

    public static final int VOICEHOLDER = 1;
    public static final int VOICECLIENT = 2;
    public static final int VOICEAUDITOR = 3;

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
    @SerializedName("name")
    public String name;
    @SerializedName("logo")
    public String logo;
    @SerializedName("isMute")
    public boolean isMute;
    @SerializedName("isJoinChannel")
    public boolean isJoinChannel;

}
