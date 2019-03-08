package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

public class BaseVoiceRole {

    public static final int VOICEHOLDER = 1;
    public static final int VOICECLIENT = 2;
    public static final int VOICEAUDITOR = 3;

    public int voiceRole;
    public String channelId;
    public boolean isJoinChannel;
    public boolean isMute;

}
