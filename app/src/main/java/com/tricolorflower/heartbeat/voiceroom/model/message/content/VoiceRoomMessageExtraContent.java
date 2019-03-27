package com.tricolorflower.heartbeat.voiceroom.model.message.content;


import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;
import com.tricolorflower.heartbeat.voiceroom.model.voiceroom.VoiceRoom;

public class VoiceRoomMessageExtraContent {

    @SerializedName("from_user")
    public VoiceRole from;
    @SerializedName("to_user")
    public VoiceRole to;
    @SerializedName("room_info")
    public VoiceRoom voiceRoom;
//    @SerializedName("room_info")
//    public VoiceRoom voiceRoom;
//    @SerializedName("'driver_info")
//    public ProductList.Product voiceRoom;

}
