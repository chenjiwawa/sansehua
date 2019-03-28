package com.tricolorflower.heartbeat.voiceroom.model.message;

import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.content.BaseMessageExtraContent;

import java.io.Serializable;

public class BaseMessageExtra implements Serializable {

    @SerializedName("type")
    public String messageExtraType;

    @SerializedName("is_display")
    public String isDisplay;

    @SerializedName("content")
    public BaseMessageExtraContent messageExtraContent;

    @SerializedName("extra")
    public String messageExtraExtra;

}