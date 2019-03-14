package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseMessage implements Serializable {

    public static final int CLIENT_ROLE_AUDIENCE_2_BROADCASTER = 11;
    public static final int CLIENT_ROLE_BROADCASTER_2_AUDIENCE = 12;

    public static final int PRUDCT_GIFT_ME_2_OTHER = 21;
    public static final int PRUDCT_DIAMOND_ME_2_OTHER = 22;

    public static final int THEME_HOLDER_2_OTHER = 31;
    public static final int THEME_ADMIN_2_OTHER = 32;

    public static final int AVATAR_ANIM_ME_2_OTHER = 41;

    public static final int MUSIC_HOLDER_2_OTHER = 51;
    public static final int MUSIC_ADMIN_2_OTHER = 52;

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

}
