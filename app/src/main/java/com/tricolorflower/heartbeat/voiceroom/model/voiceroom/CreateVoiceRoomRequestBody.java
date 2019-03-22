package com.tricolorflower.heartbeat.voiceroom.model.voiceroom;

import com.qsmaxmin.qsbase.common.model.QsModel;
import com.tricolorflower.heartbeat.common.model.BaseRequstBody;

public class CreateVoiceRoomRequestBody extends BaseRequstBody {

    public String room_type;

    public CreateVoiceRoomRequestBody(String token, String room_type) {
        super(token);
        this.room_type = room_type;
    }

    @Override
    public String toString() {
        return "CreateVoiceRoomRequestBody{" +
                "room_type='" + room_type + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
