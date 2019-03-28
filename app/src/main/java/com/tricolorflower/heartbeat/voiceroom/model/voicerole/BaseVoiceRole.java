package com.tricolorflower.heartbeat.voiceroom.model.voicerole;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseVoiceRole implements Serializable {

     /*
     空麦位判断

     isVoiceRoleUsingPosition //房主麦位 8个麦位 是否有人

     isVoicePositionEmpty//当前用户 对应的麦位 没有对应麦位（-1）

     */

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

    public static final int POSITION_EMPTY = -1;
    //房主 0 麦上用户1-8
    @SerializedName("position")
    public int position;

    //空麦位
    public boolean isVoicePositionEmpty() {
        return position == POSITION_EMPTY ? true : false;
    }

    //房主麦位
    public boolean isVoiceHolderPosition() {
        return position == 0 ? true : false;
    }

    //麦上用户麦位
    public boolean isVoiceClientPosition() {
        return (1 <= position && position <= 8) ? true : false;
    }

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
        if ((permission == PERMISSION_ADMIN || permission == PERMISSION_GUEST) && isVoiceClientPosition()) {
            return true;
        }
        return false;
    }

    public boolean isVoiceAuditor() {
        if ((permission == PERMISSION_ADMIN || permission == PERMISSION_GUEST) && isVoicePositionEmpty()) {
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

    //是否麦位
    public boolean isVoiceRoleUsingPosition() {
        return (id != 0 ? true : false);
    }

}
