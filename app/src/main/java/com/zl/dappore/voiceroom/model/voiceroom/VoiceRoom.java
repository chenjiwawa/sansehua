package com.zl.dappore.voiceroom.model.voiceroom;

import com.google.gson.annotations.SerializedName;
import com.zl.dappore.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class VoiceRoom extends BaseModel implements Serializable {

    /*房间*/
    @SerializedName("room_id")
    public String voiceRoomId;
    @SerializedName("room_name")
    public String voiceRoomName;
    @SerializedName("logo")
    public String voiceRoomLogo;
    @SerializedName("type")
    public int voiceRoomType;
    @SerializedName("typeName")
    public String voiceRoomTypeName;
    @SerializedName("room_member")
    public String voiceRoomOnlines;
    @SerializedName("room_announce")
    public String voiceRoomAnnounce;
    @SerializedName("greeting")
    public String voiceRoomGreeting;
    @SerializedName("isLock")
    public boolean isLock;
    @SerializedName("password")
    public String voiceRoomPwd;
    @SerializedName("isLock")
    public boolean isOpenChatRoom;

}
