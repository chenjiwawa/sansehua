package com.tricolorflower.heartbeat.voiceroom.model.message.extra.content;


import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;

public class VoiceRoomVehicleMessageExtraContent extends VoiceRoomWithFromMessageExtraContent{

    @SerializedName("'driver_info")
    public ProductList.Product vehicle;

}
