package com.tricolorflower.heartbeat.voiceroom.model.message.extra.content;


import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.EmojiList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.ProductList;
import com.tricolorflower.heartbeat.voiceroom.model.voicerole.VoiceRole;

import java.util.List;

public class VoiceRoomGiftMessageExtraContent extends VoiceRoomWithFromToMessageExtraContent{

    @SerializedName("'gift_info")
    public ProductList.Product gift;

}
