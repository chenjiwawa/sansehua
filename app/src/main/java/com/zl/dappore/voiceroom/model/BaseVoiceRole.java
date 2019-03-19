package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseVoiceRole implements Serializable {

    public static final int VOICE_AUDITOR = 0;
    public static final int VOICE_ADMIN_AUDITOR = 1;
    public static final int VOICE_CLIENT = 2;
    public static final int VOICE_ADMIN_CLIENT = 3;
    public static final int VOICE_HOLDER = 5;

    //     1普通观众    2房主   3管理员
    public static final int ROLE_AUDITOR = 1;
    public static final int ROLE_HOLDER = 2;
    public static final int ROLE_ADMIN = 3;

    @SerializedName("uid")
    public String id;
    @SerializedName("nickname")
    public String name;
    @SerializedName("pic")
    public String logo;

    /*声网语音直播*/
    public String agoraChannelName;
    public String agoraUid;

    /*融云IM*/
    public String rongChatRoomId;
    public int rongChatRoomType;

    /*控制状态*/
    @SerializedName("role")
    public int voiceRole;//1普通观众   2房主   3管理员
    @SerializedName("turn_music")
    public boolean musicEnable;// 1关闭音乐权限，2为打开
    @SerializedName("is_voice")
    public boolean voiceMute;// 1未关闭，2为打开
    @SerializedName("is_lock")
    public boolean voiceEnable;// 1没有上锁，2为上锁


    public boolean isVoiceClient() {
        return (voiceRole == VOICE_CLIENT || voiceRole == VOICE_ADMIN_CLIENT ? true : false);
    }

    public boolean isVoiceAuditor() {
        return (voiceRole == VOICE_AUDITOR || voiceRole == VOICE_ADMIN_AUDITOR ? true : false);
    }

    public boolean isVoiceAdmin() {
        return (voiceRole == VOICE_ADMIN_AUDITOR || voiceRole == VOICE_ADMIN_CLIENT || voiceRole == VOICE_HOLDER ? true : false);
    }

    public boolean isVoiceHolder() {
        return (voiceRole == VOICE_HOLDER ? true : false);
    }

}
