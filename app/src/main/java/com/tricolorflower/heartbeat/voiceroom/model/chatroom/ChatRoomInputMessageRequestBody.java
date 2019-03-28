package com.tricolorflower.heartbeat.voiceroom.model.chatroom;

import com.tricolorflower.heartbeat.common.model.BaseRequstBody;
import com.tricolorflower.heartbeat.common.model.BaseVoiceRoomRequestBody;

public class ChatRoomInputMessageRequestBody extends BaseVoiceRoomRequestBody {

    public static final String TYPE_TEXT = "RC:TxtMsg";

    public String content;
    public String type;//RC:TxtMsg

    public ChatRoomInputMessageRequestBody(String token, String room_id, String content, String type) {
        super(token, room_id);
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ChatRoomInputMessageRequestBody{" +
                "content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", room_id='" + room_id + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
