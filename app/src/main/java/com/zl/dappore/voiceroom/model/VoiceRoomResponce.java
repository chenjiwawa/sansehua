package com.zl.dappore.voiceroom.model;

import com.google.gson.annotations.SerializedName;
import com.zl.dappore.common.model.BaseModel;

import java.io.Serializable;
import java.util.List;

public class VoiceRoomResponce extends BaseModel implements Serializable {

    /*房间*/
    @SerializedName("room_info")
    public VoiceRoom voiceRoom;

    /*房主*/
    @SerializedName("anchor")
    public VoiceRole voiceHolder;

    @SerializedName("current_role")
    public int voiceUserRole;

    /*麦位*/
    @SerializedName("seat_config")
    public List<VoiceRole> voiceClients;

    /*声网语音直播*/

    /*融云IM*/

}
