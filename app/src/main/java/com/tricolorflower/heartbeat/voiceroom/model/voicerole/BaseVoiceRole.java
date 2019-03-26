package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseVoiceRole implements Serializable {

    /*
     按麦分类
     观众    房主    麦上用户
     */
    public static final int VOICE_AUDITOR = 11;
    public static final int VOICE_CLIENT = 22;
    public static final int VOICE_HOLDER = 33;
    public static final int VOICE_ADMIN_CLIENT = 44;
    public static final int VOICE_ADMIN_AUDITOR = 55;

    /*
    按权限分类
    1普通观众    2房主   3管理员
    */
    public static final int PERMISSION_GUEST = 1;
    public static final int PERMISSION_HOLDER = 2;
    public static final int PERMISSION_ADMIN = 3;

    @SerializedName("uid")
    public int id;
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
    public int voiceRole;//1普通观众   2房主   3管理员
    @SerializedName("role")
    public int permission;//1普通观众   2房主   3管理员
    @SerializedName("turn_music")
    public int musicEnable;// 1关闭音乐权限，2为打开
    @SerializedName("is_voice")
    public int voiceMute;// 1关闭麦位声音，2打开麦位声音
    @SerializedName("is_lock")
    public int voiceEnable;// 1解除锁麦，2锁麦


    public boolean isVoiceClient() {
        return (voiceRole == VOICE_CLIENT ? true : false);
    }

    public boolean isVoiceAuditor() {
        return (voiceRole == VOICE_AUDITOR ? true : false);
    }

    public boolean isVoiceHolder() {
        return (voiceRole == VOICE_HOLDER ? true : false);
    }

    public boolean isPermissionGuest() {
        return (permission == PERMISSION_GUEST ? true : false);
    }

    public boolean isPermissionHolder() {
        return (permission == PERMISSION_HOLDER ? true : false);
    }

    public boolean isPermissionAdmin() {
        return (permission == PERMISSION_ADMIN ? true : false);
    }

    public boolean isCurrentVoiceUser(int voiceUserId) {
        if (voiceUserId != 0) {
            return (voiceUserId == id ? true : false);
        }
        return false;
    }

    public boolean isVoiceRoleUsing() {
        return (id != 0 ? true : false);
    }

}
