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

    public static final int ID_EMPTY = 0;
    @SerializedName("uid")
    public int id;

    //没有人用麦
    public boolean isIdEmpty() {
        return id == ID_EMPTY ? true : false;
    }

    @SerializedName("nickname")
    public String name;
    @SerializedName("pic")
    public String logo;
    @SerializedName("position")
    public int position;

    /*声网语音直播*/
    public String agoraChannelName;
    public String agoraUid;

    /*融云IM*/
    public String rongChatRoomId;
    public int rongChatRoomType;

    /*控制状态*/
    public int voiceRole;//1普通观众   2房主   3管理员 TODO 未用到
    @SerializedName("role")
    public int permission;//1普通观众   2房主   3管理员

    @SerializedName("turn_music")
    public int musicEnable;// 1关闭音乐权限，2为打开

    //音乐权限打开
    public boolean isMusicEnable() {
        return musicEnable == 2 ? true : false;
    }

    @SerializedName("is_voice")
    public int voiceMute;// 1关闭麦位声音，2打开麦位声音

    //麦位声音关闭
    public boolean isVoiceMute() {
        return voiceMute == 1 ? true : false;
    }

    @SerializedName("is_lock")
    public int voiceEnable;// 1解除锁麦，2锁麦

    //封麦解除（可用）
    public boolean isVoiceEnable() {
        return voiceEnable == 1 ? true : false;
    }

    public boolean isVoiceClient() {
        if ((permission == PERMISSION_ADMIN || permission == PERMISSION_GUEST) && position > 0) {
            return true;
        }
        return false;
    }

    public boolean isVoiceAuditor() {
        if ((permission == PERMISSION_ADMIN || permission == PERMISSION_GUEST) && position == 0) {
            return true;
        }
        return false;
    }

    public boolean isVoiceHolder() {
        return (permission == PERMISSION_HOLDER ? true : false);
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

    public boolean isVoiceRoleUsingPosition() {
        return (id != 0 ? true : false);
    }

}
