package com.tricolorflower.heartbeat.voiceroom.model.message;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

public class VoiceRoomMessage {

    @SerializedName("name")
    public String type;

    @SerializedName("name")
    public VoiceRole user;
    @SerializedName("name")
    public VoiceRole target;
    @SerializedName("name")
    public ProductList.Product product;
//    @SerializedName("name")
//    public ProductList.Product product;

}
