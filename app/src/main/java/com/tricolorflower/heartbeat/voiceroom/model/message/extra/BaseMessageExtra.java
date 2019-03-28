package com.tricolorflower.heartbeat.voiceroom.model.message.extra;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.content.BaseMessageExtraContent;
import com.tricolorflower.heartbeat.voiceroom.model.message.extra.type.MessageExtraTypeConstants;

import java.io.Serializable;

public class BaseMessageExtra implements Serializable {

    @SerializedName("type")
    public String messageExtraType;

    @SerializedName("is_display")
    public String isDisplay;

//    @SerializedName("content")
//    public BaseMessageExtraContent messageExtraContent;

    @SerializedName("extra")
    public String messageExtraExtra;

    protected BaseMessageExtra parse(String messageExtra) {
        Gson gson = new Gson();
        return gson.fromJson(messageExtra, BaseMessageExtra.class);
    }

    public void handleMessageExtraType(String messageExtraType) {
        switch (messageExtraType) {
            case MessageExtraTypeConstants.microseat_message:
                break;
            case MessageExtraTypeConstants.room_message:
                break;
            case MessageExtraTypeConstants.admin_message:
                break;
            case MessageExtraTypeConstants.chat_message:
                break;
            case MessageExtraTypeConstants.gift_message:
                break;
            case MessageExtraTypeConstants.emoji_message:
                break;
        }
    }

}