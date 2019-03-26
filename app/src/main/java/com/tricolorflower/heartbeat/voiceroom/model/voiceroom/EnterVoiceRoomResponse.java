package com.tricolorflower.heartbeat.voiceroom.model.voiceroom;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.common.model.BaseModel;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

import java.io.Serializable;
import java.util.List;

public class EnterVoiceRoomResponse extends BaseModel implements Serializable {

    /*房间*/
    @SerializedName("room_info")
    public VoiceRoom voiceRoom;

    /*房主*/
    @SerializedName("anchor")
    public VoiceRole voiceHolder;

    @SerializedName("current_role")
    public int voiceUserPermission;

    /*麦位*/
    @SerializedName("seat_config")
    public List<VoiceRole> voiceClients;

    /*声网语音直播*/

    /*融云IM*/

}
